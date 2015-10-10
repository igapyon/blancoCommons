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

import java.io.UnsupportedEncodingException;

/**
 * blanco Framework�ɂ�����A������Ɋւ��郆�[�e�B���e�B�̓����������܂܂�܂��B
 * 
 * ���̃N���X�̓p�b�P�[�W�O����J�Ɛݒ肵�Ă��܂��B
 * 
 * @author IGA Tosiki
 */
class BlancoStringUtilPad {
    /**
     * �w��̒����ɂȂ�܂ŁA������̉E���ɕ�����ǉ����܂��B
     * 
     * @param argSource
     *            �I���W�i��������B
     * @param argLength
     *            ��]���钷���B
     * @param argPadChar
     *            �l�ߍ��݂ɗ��p���镶���B
     * @return �w��̒����ɉ��H��̕�����B�w��̒��������I���W�i�������񂪒����ꍇ�ɂ́A�I���W�i�������񂪂��̂܂ܖ߂�܂��B
     */
    public static final String padRight(final String argSource,
            final int argLength, final char argPadChar) {
        if (argSource == null) {
            throw new IllegalArgumentException(
                    "BlancoStringUtil.padRight�̓��͕������null���^�����܂����B");
        }

        final StringBuffer buf = new StringBuffer();
        buf.append(argSource);

        for (; buf.length() < argLength;) {
            // �ꕶ�������₵�Ă����܂��B
            buf.append(argPadChar);
        }

        return buf.toString();
    }

    /**
     * �w��̒����ɂȂ�܂ŁA������̍����ɕ�����ǉ����܂��B
     * 
     * @param argSource
     *            �I���W�i��������B
     * @param argLength
     *            ��]���钷���B
     * @param argPadChar
     *            �l�ߍ��݂ɗ��p���镶���B
     * @return �w��̒����ɉ��H��̕�����B�w��̒��������I���W�i�������񂪒����ꍇ�ɂ́A�I���W�i�������񂪂��̂܂ܖ߂�܂��B
     */
    public static final String padLeft(final String argSource,
            final int argLength, final char argPadChar) {
        if (argSource == null) {
            throw new IllegalArgumentException(
                    "BlancoStringUtil.padLefo�̓��͕������null���^�����܂����B");
        }

        final int originalLength = argSource.length();
        final StringBuffer buf = new StringBuffer();

        for (; buf.length() + originalLength < argLength;) {
            // �ꕶ�������₵�Ă����܂��B
            buf.append(argPadChar);
        }

        return buf.toString() + argSource;
    }

    /**
     * Windows-31J ���Z�Ŏw��̒����ɂȂ�܂ŁA������̉E���ɕ�����ǉ����܂��B
     * 
     * @param argSource
     *            �I���W�i��������B
     * @param argLength
     *            ��]���钷���B
     * @param argPadChar
     *            �l�ߍ��݂ɗ��p���镶���B
     * @return �w��̒����ɉ��H��̕�����B�w��̒��������I���W�i�������񂪒����ꍇ�ɂ́A�I���W�i�������񂪂��̂܂ܖ߂�܂��B
     */
    public static final String padRightWindows31J(final String argSource,
            final int argLength, final char argPadChar) {
        if (argSource == null) {
            throw new IllegalArgumentException(
                    "BlancoStringUtil.padRight�̓��͕������null���^�����܂����B");
        }

        final StringBuffer buf = new StringBuffer();
        buf.append(argSource);

        try {
            for (; buf.toString().getBytes("Windows-31J").length < argLength;) {
                // �ꕶ�������₵�Ă����܂��B
                buf.append(argPadChar);
            }
            return buf.toString();
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalArgumentException(
                    "�z�肵�Ȃ���O���������܂����BWindows-31J �G���R�[�f�B���O���擾�ł��܂���ł����B", ex);
        }
    }

    /**
     * Windows-31J ���Z�Ŏw��̒����ɂȂ�܂ŁA������̍����ɕ�����ǉ����܂��B
     * 
     * @param argSource
     *            �I���W�i��������B
     * @param argLength
     *            ��]���钷���B
     * @param argPadChar
     *            �l�ߍ��݂ɗ��p���镶���B
     * @return �w��̒����ɉ��H��̕�����B�w��̒��������I���W�i�������񂪒����ꍇ�ɂ́A�I���W�i�������񂪂��̂܂ܖ߂�܂��B
     */
    public static final String padLeftWindows31J(final String argSource,
            final int argLength, final char argPadChar) {
        if (argSource == null) {
            throw new IllegalArgumentException(
                    "BlancoStringUtil.padLefo�̓��͕������null���^�����܂����B");
        }

        try {
            final int originalLength = argSource.getBytes("Windows-31J").length;
            final StringBuffer buf = new StringBuffer();

            for (; buf.toString().getBytes("Windows-31J").length
                    + originalLength < argLength;) {
                // �ꕶ�������₵�Ă����܂��B
                buf.append(argPadChar);
            }
            return buf.toString() + argSource;
        } catch (UnsupportedEncodingException ex) {
            throw new IllegalArgumentException(
                    "�z�肵�Ȃ���O���������܂����BWindows-31J �G���R�[�f�B���O���擾�ł��܂���ł����B", ex);
        }
    }
}
