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

/**
 * blanco Framework�ɂ�����A������Ɋւ��郆�[�e�B���e�B�̓����������܂܂�܂��B
 * 
 * ���̃N���X�̓p�b�P�[�W�O����J�Ɛݒ肵�Ă��܂��B
 * 
 * @author IGA Tosiki
 */
class BlancoStringUtilTrim {
    /**
     * �^����ꂽ������ɂ��āA�����ɔ��p�󔒂������ ������������܂��B
     * 
     * ���p�󔒂̂ݏ������܂��B�S�p�󔒂͏������܂���B
     * 
     * @param originalString
     *            �������s������������B
     * @return ���p�󔒂��؂�l�߂�ꂽ��̕�����B
     */
    public static final String trimLeft(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "������؂�l��(��)���\�b�h�ɁA�ϊ���������Ƃ���null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }
        int lastSpace = -1;
        for (int index = 0; index < originalString.length(); index++) {
            if (originalString.charAt(index) != ' ') {
                break;
            }
            lastSpace = index;
        }
        if (lastSpace < 0) {
            return originalString;
        }
        return originalString.substring(lastSpace + 1);
    }

    /**
     * �^����ꂽ������ɂ��āA�E���ɔ��p�󔒂������ ������������܂��B
     * 
     * ���p�󔒂̂ݏ������܂��B�S�p�󔒂͏������܂���B
     * 
     * @param originalString
     *            �������s������������B
     * @return ���p�󔒂��؂�l�߂�ꂽ��̕�����B
     */
    public static final String trimRight(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "������؂�l��(�E)���\�b�h�ɁA�ϊ���������Ƃ���null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }
        int lastSpace = -1;
        for (int index = originalString.length() - 1; index >= 0; index--) {
            if (originalString.charAt(index) != ' ') {
                break;
            }
            lastSpace = index;
        }
        if (lastSpace < 0) {
            return originalString;
        }
        return originalString.substring(0, lastSpace);
    }

    /**
     * �^����ꂽ������ɂ��āA�E������э����ɔ��p�󔒂������ ������������܂��B
     * 
     * ���p�󔒂̂ݏ������܂��B�S�p�󔒂͏������܂���B
     * 
     * @param originalString
     *            �������s������������B
     * @return ���p�󔒂��؂�l�߂�ꂽ��̕�����B
     */
    public static final String trim(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "������؂�l��(���E)���\�b�h�ɁA�ϊ���������Ƃ���null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }
        return trimRight(trimLeft(originalString));
    }
}
