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

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * blanco Framework�ɂ����� Java�\�[�X�R�[�h�o�͂̂��߂̃��[�e�B���e�B���W�߂��N���X�ł��B
 * 
 * ���̃N���X�� C#.NET�̃\�[�X�R�[�h������������p����܂��B
 * 
 * @author IGA Tosiki
 */
public final class BlancoJavaSourceUtil {
    /**
     * �^����ꂽ�������Java�\�[�X�R�[�h������Ƃ��ďo�͂�����̂Ƃ��ăG�X�P�[�v�������܂��B
     * 
     * ��/�o�b�N�X���b�V���̃G�X�P�[�v����щ��s�R�[�h�̃G�X�P�[�v���s���܂��B<br>
     * ����ȊO�̏����͍s���܂���B���Ƃ��΃C���W�F�N�V�����U���Ȃǂւ̑ϐ��́A���̃��\�b�h�͈����܂���B
     * 
     * @param originalString
     *            ���͕�����
     * @return �G�X�P�[�v�������s��ꂽ��̕�����
     */
    public static final String escapeStringAsJavaSource(
            final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "BlancoJavaSourceUtil.escapeStringAsJavaSource�œ��͈ᔽ�������B���̃��\�b�h��null���p�����[�^�Ƃ��ė^�����܂����Bnull�ȊO�̒l����͂��Ă��������B");
        }

        final StringReader reader = new StringReader(originalString);
        final StringWriter writer = new StringWriter();
        try {
            for (;;) {
                final int iRead = reader.read();
                if (iRead < 0) {
                    break;
                }
                switch (iRead) {
                case '\\':
                    writer.write("\\\\");
                    break;
                case '\n':
                    writer.write("\\n");
                    break;
                case '"':
                    writer.write("\\\"");
                    break;
                default:
                    writer.write((char) iRead);
                    break;
                }
            }
            writer.flush();
        } catch (IOException e) {
            // �����ɓ����Ă��邱�Ƃ́A���肦�܂���B
            e.printStackTrace();
        }
        return writer.toString();
    }

    /**
     * �^����ꂽ�������JavaDoc������Ƃ��Ĉ������Ƃ��ł�����̂ւƃG�X�P�[�v�������܂��B JavaDoc������Ƃ��ăG�X�P�[�v���s���܂��B
     * 
     * HTML�Ƃ��ẴG�X�P�[�v�Ɠ����̏������s���܂��B�������h���G�X�P�[�v����܂��B
     * 
     * @param originalString
     *            ���͕�����
     * @return �G�X�P�[�v�������s��ꂽ��̕�����
     */
    public static final String escapeStringAsJavaDoc(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "BlancoJavaSourceUtil.escapeStringAsJavaDoc�œ��͈ᔽ�������B���̃��\�b�h��null���p�����[�^�Ƃ��ė^�����܂����Bnull�ȊO�̒l����͂��Ă��������B");
        }

        final StringReader reader = new StringReader(originalString);
        final StringWriter writer = new StringWriter();
        try {
            for (;;) {
                final int iRead = reader.read();
                if (iRead < 0) {
                    break;
                }
                switch (iRead) {
                case '&':
                    writer.write("&amp;");
                    break;
                case '<':
                    writer.write("&lt;");
                    break;
                case '>':
                    writer.write("&gt;");
                    break;
                case '"':
                    writer.write("&quot;");
                    break;
                case '\n':
                    // ���s�����̂܂�JavaDoc�ɏo�͂����Ƃ܂����̂ŁA������Ƃ��Ĉ����܂��B
                    writer.write("\\n");
                    break;
                default:
                    writer.write((char) iRead);
                    break;
                }
            }
            writer.flush();
        } catch (IOException e) {
            // �����ɓ����Ă��邱�Ƃ́A���肦�܂���B
            e.printStackTrace();
        }
        return writer.toString();
    }
}
