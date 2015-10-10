/*
 * blanco Framework
 * Copyright (C) 2004-2009 IGA Tosiki
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
/*******************************************************************************
 * Copyright (c) 2009 IGA Tosiki, NTT DATA BUSINESS BRAINS Corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IGA Tosiki (NTT DATA BUSINESS BRAINS Corp.) - initial API and implementation
 *******************************************************************************/
package blanco.commons.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * blanco Framework�ɂ�����t�@�C���֘A�̃��[�e�B���e�B���W�߂��N���X�ł��B
 * 
 * @author IGA Tosiki
 */
public class BlancoFileUtil {
    /**
     * �f�o�b�O���[�h�œ��삷�邩�ǂ����̃t���O�B
     */
    private static final boolean IS_DEUBG = false;

    /**
     * �^����ꂽ���̓t�@�C�����o�̓t�@�C���ւƃR�s�[���܂��B
     * 
     * ���̓t�@�C�������݂��Ȃ��ꍇ�Ȃǂɂ͗�O���������܂��B
     * 
     * @param fileInput
     *            ���̓t�@�C���Bnull�͗^���Ȃ��ł��������B
     * @param fileOutput
     *            �o�̓t�@�C���Bnull�͗^���Ȃ��ł��������B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    public static final void copy(final File fileInput, final File fileOutput)
            throws IOException {
        if (IS_DEUBG) {
            System.out.println("BlancoFileUtil.copy("
                    + fileInput.getAbsolutePath() + ", "
                    + fileOutput.getAbsolutePath() + ")");
        }
        if (fileInput == null) {
            throw new IllegalArgumentException("���̓t�@�C����null���^�����܂����B");
        }
        if (fileOutput == null) {
            throw new IllegalArgumentException("�o�̓t�@�C����null���^�����܂����B");
        }
        if (fileInput.exists() == false) {
            throw new IllegalArgumentException("���̓t�@�C��["
                    + fileInput.getAbsolutePath() + "]��������܂���B");
        }
        if (fileInput.isFile() == false) {
            throw new IllegalArgumentException("���̓t�@�C��["
                    + fileInput.getAbsolutePath() + "]�Ƀf�B���N�g����^���邱�Ƃ͂ł��܂���B");
        }
        if (fileOutput.exists()) {
            if (fileOutput.isFile() == false) {
                throw new IllegalArgumentException("�o�̓t�@�C��["
                        + fileInput.getAbsolutePath() + "]�Ƀf�B���N�g����^���邱�Ƃ͂ł��܂���B");
            }
            if (fileOutput.canWrite() == false) {
                throw new IllegalArgumentException("�o�̓t�@�C��["
                        + fileInput.getAbsolutePath()
                        + "]�����݂��������ɁA�������݂��s�����Ƃ��ł��܂���B");
            }
        }

        final File fileTargetParent = fileOutput.getParentFile();
        if (fileTargetParent.exists() == false) {
            // ���݂��Ȃ��ꍇ�ɂ͍쐬���܂��B
            if (fileTargetParent.mkdirs() == false) {
                throw new IllegalArgumentException("�o�̓t�@�C��["
                        + fileInput.getAbsolutePath() + "]�̐e�t�H���_["
                        + fileTargetParent.getAbsolutePath()
                        + "]�����݂��Ȃ������̂ō쐬���悤�Ƃ��܂������쐬�Ɏ��s���܂����B");
            }
        }

        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new BufferedInputStream(new FileInputStream(fileInput));
            outStream = new BufferedOutputStream(new FileOutputStream(
                    fileOutput));

            // �X�g���[���̃R�s�[���s���܂��B
            BlancoStreamUtil.copy(inStream, outStream);

            outStream.flush();
        } finally {
            try {
                // �X�g���[�����J���Ă���ꍇ�ɂ͊m���ɃN���[�Y���܂��B
                if (inStream != null) {
                    inStream.close();
                }
            } finally {
                // �X�g���[�����J���Ă���ꍇ�ɂ͊m���ɃN���[�Y���܂��B
                if (outStream != null) {
                    outStream.close();
                }
            }
        }
    }

    /**
     * �^����ꂽ���̓X�g���[�����o�̓X�g���[���ւƃR�s�[���܂��B
     * 
     * ���̃��\�b�h�̓����ł̓t���b�V�������͍s���܂���B�K�v�ɉ����ČĂяo�������\�b�h�ɂ����� flush()���Ă��������B<br>
     * �����I�ɂ� �ʃN���X�̃X�g���[���R�s�[���Ăяo���܂��B
     * 
     * @deprecated ���̃��\�b�h�̑���� BlancoStreamUtil.copy���\�b�h���Ăяo�����Ƃ������߂��܂��B
     * @param inStream
     *            ���̓X�g���[���B
     * @param outStream
     *            �o�̓X�g���[���B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    public static final void copy(final InputStream inStream,
            final OutputStream outStream) throws IOException {
        if (inStream == null) {
            throw new IllegalArgumentException(
                    "BlancoFileUtil.copy���\�b�h�̓��̓X�g���[���p�����[�^��null���^�����܂����Bnull�ȊO�̒l���w�肵�Ă��������B");
        }
        if (outStream == null) {
            throw new IllegalArgumentException(
                    "BlancoFileUtil.copy���\�b�h�̏o�̓X�g���[���p�����[�^��null���^�����܂����Bnull�ȊO�̒l���w�肵�Ă��������B");
        }

        BlancoStreamUtil.copy(inStream, outStream);
    }

    /**
     * �t�@�C����ǂݍ��݁Abyte�z��ւƓW�J���܂��B
     * 
     * @param inputFile
     *            ���̓t�@�C���B
     * @return byte�z�񉻂��ꂽ�t�@�C���̓��e�B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     * @throws IllegalArgumentException
     *             �t�@�C�������݂��Ȃ��Ȃǂ̏ꍇ�B
     */
    public static final byte[] file2Bytes(final File inputFile)
            throws IOException, IllegalArgumentException {
        if (inputFile == null) {
            throw new IllegalArgumentException(
                    "file2Bytes()���\�b�h�ɓ��̓p�����[�^�Ƃ���null���^������܂����B");
        }
        if (inputFile.exists() == false) {
            throw new IllegalArgumentException(
                    "file2Bytes()���\�b�h�ɓ��̓p�����[�^�Ƃ��ė^����ꂽ�t�@�C���͑��݂��܂���B");
        }
        if (inputFile.canRead() == false) {
            throw new IllegalArgumentException(
                    "file2Bytes()���\�b�h�ɓ��̓p�����[�^�Ƃ��ė^����ꂽ�t�@�C���͓ǂݍ��ނ��Ƃ��ł��܂���B");
        }
        if (inputFile.isDirectory()) {
            throw new IllegalArgumentException(
                    "file2Bytes()���\�b�h�ɓ��̓p�����[�^�Ƃ��ăf�B���N�g�����^�����܂����B�f�B���N�g���͏����ł��܂���");
        }

        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        final InputStream inStream = new BufferedInputStream(
                new FileInputStream(inputFile), BlancoStreamUtil.BUF_SIZE);
        try {
            BlancoStreamUtil.copy(inStream, outStream);
            outStream.flush();
            return outStream.toByteArray();
        } finally {
            inStream.close();
            outStream.close();
        }
    }

    /**
     * �^����ꂽ�o�C�g�z����A�^����ꂽ�t�@�C���ɏo�͂��܂��B
     * 
     * @param inputBytes
     *            ���͂ƂȂ�o�C�g�z��B
     * @param outputFile
     *            �o�͂ƂȂ�t�@�C���B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     * @throws IllegalArgumentException
     *             �t�@�C����null���^����ꂽ�Ȃǂ̏ꍇ�B
     */
    public static final void bytes2File(final byte[] inputBytes,
            final File outputFile) throws IOException, IllegalArgumentException {
        if (inputBytes == null) {
            throw new IllegalArgumentException(
                    "bytes2File()���\�b�h�ɓ��̓p�����[�^�̓��̓o�C�g�z��Ƃ���null���^������܂����B");
        }
        if (outputFile == null) {
            throw new IllegalArgumentException(
                    "bytes2File()���\�b�h�ɓ��̓p�����[�^�̏o�̓t�@�C���Ƃ���null���^������܂����B");
        }
        if (outputFile.exists()) {
            if (outputFile.isDirectory()) {
                throw new IllegalArgumentException(
                        "bytes2File()���\�b�h�ɓ��̓p�����[�^�Ƃ��ďo�͐�t�@�C���Ƀf�B���N�g�����^�����܂����B�f�B���N�g���͏����ł��܂���");
            }
            if (outputFile.canWrite() == false) {
                throw new IllegalArgumentException(
                        "bytes2File()���\�b�h�ɓ��̓p�����[�^�Ƃ��ė^����ꂽ�t�@�C���͑��݂����ɏ������݂ł��܂���B");
            }
        } else {
            if (outputFile.createNewFile() == false) {
                throw new IllegalArgumentException(
                        "bytes2File()���\�b�h�ɓ��̓p�����[�^�Ƃ��ďo�͐�t�@�C���͐������邱�Ƃ��ł��܂���B");
            }
        }

        final ByteArrayInputStream inStream = new ByteArrayInputStream(
                inputBytes);
        final OutputStream outStream = new BufferedOutputStream(
                new FileOutputStream(outputFile), BlancoStreamUtil.BUF_SIZE);
        try {
            BlancoStreamUtil.copy(inStream, outStream);
            outStream.flush();
        } finally {
            outStream.close();
            inStream.close();
        }
    }

    /**
     * �K�v�ɉ����āA�o�C�g�z����t�@�C���ɏo�͂��܂��B
     * 
     * @param inputBytes
     *            ���̓o�C�g�z��B
     * @param outputFile
     *            �o�͐�t�@�C���B
     * @return 0:�쐬���X�V�������Ȃ�Ȃ������B1:�t�@�C����V�K�ɍ쐬�����B2:�t�@�C�����X�V�����B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    public static final int bytes2FileIfNecessary(final byte[] inputBytes,
            final File outputFile) throws IOException {

        byte[] originalFileImage = null;
        if (outputFile.exists()) {
            // �I���W�i���̃t�@�C���C���[�W���擾���Ă����܂��B
            originalFileImage = file2Bytes(outputFile);
        }

        if (originalFileImage == null) {
            // �L�������킳���V�K�������݁B
            bytes2File(inputBytes, outputFile);
            return 1;
        } else {
            if (BlancoByteUtil.compare(originalFileImage, inputBytes) == 0) {
                // �������ޕK�v�Ȃ��B
                return 0;
            } else {
                // �X�V�������݁B
                bytes2File(inputBytes, outputFile);
                return 2;
            }
        }
    }

    /**
     * ���s���擾���܂��B
     * 
     * @return ���s�BWindows �Ȃ� \n
     */
    public static final String getNewLine() {
        return System.getProperty("line.separator");
    }
}
