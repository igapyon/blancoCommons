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
 * blanco Framework�ɂ�����A������Ɋւ��郆�[�e�B���e�B�̓����������܂܂�܂��B
 * 
 * ���̃N���X�̓p�b�P�[�W�O����J�Ɛݒ肵�Ă��܂��B
 * 
 * @author IGA Tosiki
 */
class BlancoStringUtilReplace {
    /**
     * ���K�\���Ƃ͊֌W�Ȃ�������̒u�����s���܂��B
     * 
     * ���̃��\�b�h�ł� java.lang.String�Ƃ͈قȂ萳�K�\���͊֗^���܂���B<br>
     * ���K�\�����@�\���Ăق����Ȃ�������u���̍ۂɗ��p���܂��B
     * 
     * @param source
     *            �ϊ��O�̕�����B
     * @param replaceFrom
     *            �u���������B
     * @param replaceTo
     *            �u���敶���B
     * @return �ϊ���̕�����B
     */
    public static final String replaceAll(final String source,
            final char replaceFrom, final char replaceTo) {
        if (source == null) {
            throw new IllegalArgumentException(
                    "������u���̃��\�b�h�ɁA�ϊ���������Ƃ���null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }

        try {
            final StringReader reader = new StringReader(source);
            final StringWriter writer = new StringWriter();
            try {
                for (;;) {
                    int iRead = reader.read();
                    if (iRead < 0) {
                        break;
                    }
                    if (iRead == replaceFrom) {
                        iRead = replaceTo;
                    }
                    writer.write(iRead);
                }
                return writer.toString();
            } finally {
                reader.close();
                writer.close();
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(
                    "������u���̃��\�b�h�̏������ɁA�z�肳��Ȃ���O���������܂����B�������f���܂��B" + ex.toString());
        }
    }

    /**
     * ���K�\���Ƃ͊֌W�Ȃ�������̒u�����s���܂��B
     * 
     * ���̃��\�b�h�ł� java.lang.String�Ƃ͈قȂ萳�K�\���͊֗^���܂���B<br>
     * ���K�\�����@�\���Ăق����Ȃ�������u���̍ۂɗ��p���܂��B
     * 
     * @param source
     *            �ϊ��O�̕�����B
     * @param replaceFrom
     *            �u����������B
     * @param replaceTo
     *            �u���敶����B
     * @param isReplaceAll
     *            �S�Ēu�����邩�ǂ����B
     * @return �ϊ���̕�����B
     */
    public static final String replace(final String source,
            final String replaceFrom, final String replaceTo,
            final boolean isReplaceAll) {
        if (source == null) {
            throw new IllegalArgumentException(
                    "������u���̃��\�b�h�ɁA�ϊ���������Ƃ���null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }
        if (replaceFrom == null) {
            throw new IllegalArgumentException(
                    "������u���̃��\�b�h�ɁA�u���������Ƃ���null���^�����܂����B");
        }
        if (replaceFrom.length() == 0) {
            throw new IllegalArgumentException(
                    "������u���̃��\�b�h�ɁA�u���������Ƃ��Ē���0�̕����񂪗^�����܂����B");
        }
        if (replaceTo == null) {
            throw new IllegalArgumentException(
                    "������u���̃��\�b�h�ɁA�u���敶���Ƃ���null���^�����܂����B");
        }

        final int indexFound = source.indexOf(replaceFrom);
        if (indexFound < 0) {
            return source;
        }
        final String pre = source.substring(0, indexFound);

        String suffix = source.substring(indexFound + replaceFrom.length());
        if (isReplaceAll) {
            final String suffixCheck = replace(suffix, replaceFrom, replaceTo,
                    false);
            if (suffix.equals(suffixCheck) == false) {
                suffix = replace(suffix, replaceFrom, replaceTo, true);
            }
        }

        return pre + replaceTo + suffix;
    }
}
