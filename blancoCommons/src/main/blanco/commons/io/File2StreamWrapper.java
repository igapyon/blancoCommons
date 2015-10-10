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
package blanco.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * �t�@�C������X�g���[���ւ̓��o�̓��b�p�[<br>
 * java.io.File����java.io.XXPutStream�n�̓��o�͂ւƕϊ����s���܂��B
 * 
 * @author IGA Tosiki
 */
public abstract class File2StreamWrapper {
    private InputStream inStream = null;

    private OutputStream outStream = null;

    /**
     * java.io.File�ŗ^����ꂽ���o�͂��X�g���[���x�[�X�ɕϊ����܂��B<br>
     * �����Е������X�g���[��������ꍇ�ɂ́A�ʂ̌o�H�ɂĎ��s�ӏ��ɗ^���Ă��������B
     * 
     * @param fileInput
     * @param fileOutput
     * @throws Exception
     */
    public File2StreamWrapper(final File fileInput, final File fileOutput)
            throws Exception {
        if (fileInput != null) {
            inStream = new BufferedInputStream(new FileInputStream(fileInput));
        }
        if (fileOutput != null) {
            outStream = new BufferedOutputStream(new FileOutputStream(
                    fileOutput));
        }
    }

    /**
     * ���o�͏��������ۂɎ��s���܂��B
     * 
     * @throws Exception
     *             ������O�����������ꍇ�B
     */
    public void run() throws Exception {
        try {
            process(inStream, outStream);

            // ����������ɏI�������ꍇ�ɂ�flush���Ăяo���ĕۗ��̏������݂����s���܂��B
            if (outStream != null) {
                outStream.flush();
            }
        } finally {
            // ���close�͒ʂ��悤�ɂ��܂��B
            closeStream();
        }
    }

    /**
     * ���ۂ̓��o�͏����������ɋL�ڂ��܂��B
     * 
     * @param inStream
     * @param outStream
     * @throws Exception
     */
    protected abstract void process(final InputStream inStream,
            final OutputStream outStream) throws Exception;

    /**
     * �X�g���[�����N���[�Y���܂��B���̃��\�b�h�̓R���X�g���N�^�����玩���I�ɌĂяo����܂��B
     * 
     * @throws IOException
     */
    protected void closeStream() throws IOException {
        try {
            if (inStream != null) {
                inStream.close();
                inStream = null;
            }
        } finally {
            if (outStream != null) {
                outStream.close();
                outStream = null;
            }
        }
    }
}
