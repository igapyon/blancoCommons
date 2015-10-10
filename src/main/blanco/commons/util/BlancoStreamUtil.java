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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * blanco Framework�ɂ�����A�X�g���[���Ɋւ��郆�[�e�B���e�B���܂܂�܂��B
 * 
 * �����Ƃ��ĂقƂ�ǂ̃��\�b�h��static���\�b�h�Ƃ��Ē񋟂���܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoStreamUtil {
    /**
     * �X�g���[���R�s�[�Ȃǂ̍ۂ̃o�b�t�@�T�C�Y�B
     */
    public static final int BUF_SIZE = 8192;

    /**
     * �^����ꂽ�X�g���[�����o�C�g�z��ɕϊ����܂��B
     * 
     * ByteArrayInputStream�̋t�̌��ʂ𓾂邽�߂̃��\�b�h�ł��B
     * 
     * @param inStream
     *            ���̓X�g���[���B
     * @return �ϊ���̃o�C�g�z��B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    public static byte[] stream2Bytes(final InputStream inStream)
            throws IOException {
        if (inStream == null) {
            throw new IllegalArgumentException(
                    "���̓X�g���[������o�C�g�z��ɕϊ����郁�\�b�h�ɓ��̓X�g���[���Ƃ���null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }

        final ByteArrayOutputStream result = new ByteArrayOutputStream();

        // �X�g���[���̃R�s�[���s���܂��B
        copy(inStream, result);

        result.flush();
        return result.toByteArray();
    }

    /**
     * �^����ꂽ���̓X�g���[�����o�̓X�g���[���ւƃR�s�[���܂��B
     * 
     * �o�b�t�@�T�C�Y8192�o�C�g�ŃX�g���[�����u���b�N�R�s�[���܂��B<br>
     * ���̃��\�b�h�̓����ł̓t���b�V�������͍s���܂���B�K�v�ɉ����ČĂяo�������\�b�h�ɂ����� flush()���Ă��������B
     * 
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
                    "BlancoStreamUtil.copy���\�b�h�̓��̓X�g���[���p�����[�^��null���^�����܂����Bnull�ȊO�̒l���w�肵�Ă��������B");
        }
        if (outStream == null) {
            throw new IllegalArgumentException(
                    "BlancoStreamUtil.copy���\�b�h�̏o�̓X�g���[���p�����[�^��null���^�����܂����Bnull�ȊO�̒l���w�肵�Ă��������B");
        }

        final byte[] buf = new byte[BUF_SIZE];
        for (;;) {
            final int length = inStream.read(buf, 0, buf.length);
            if (length < 0) {
                break;
            }
            outStream.write(buf, 0, length);
        }
    }
}
