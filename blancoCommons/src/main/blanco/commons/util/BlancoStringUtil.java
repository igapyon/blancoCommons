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
 * blanco Framework�ɂ�����A������Ɋւ��郆�[�e�B���e�B���܂܂�܂��B
 * 
 * �����Ƃ��ĂقƂ�ǂ̃��\�b�h��static���\�b�h�Ƃ��Ē񋟂���܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoStringUtil {
    /**
     * �^����ꂽbyte�z���16�i�\�L���擾���܂��B�������Ŗ߂�܂��B
     * 
     * @param arg
     *            ���͂ƂȂ�o�C�g�z��B
     * @return 16�i�\�L�̕�����B
     */
    public static final String toHexString(final byte[] arg) {
        final StringBuffer buf = new StringBuffer();
        for (int index = 0; index < arg.length; index++) {
            buf.append(toHexString(arg[index]));
        }
        return buf.toString();
    }

    /**
     * �^����ꂽbyte��16�i�\�L���擾���܂��B�������Ŗ߂�܂��B
     * 
     * @param arg
     *            �o�C�g�B
     * @return 16�i�\�L�̕�����B
     */
    public static final String toHexString(final byte arg) {
        // ���̒l�ɂ���������16�i�\�L���擾���܂��B
        String strResult = Integer.toHexString(arg & 0xff);
        for (; strResult.length() < 2;) {
            strResult = "0" + strResult;
        }
        return strResult;
    }

    /**
     * �^����ꂽchar������16�i�\�L�ɕϊ����܂��B�������Ŗ߂�܂��B
     * 
     * @param arg
     *            �����B
     * @return 16�i�\�L�̕�����B
     */
    public static final String toHexString(final char arg) {
        String strResult = Integer.toHexString(arg);
        for (; strResult.length() < 4;) {
            strResult = "0" + strResult;
        }
        return strResult;
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
     *            �u���������B
     * @param replaceTo
     *            �u���敶���B
     * @return �ϊ���̕�����B
     */
    public static final String replaceAll(final String source,
            final char replaceFrom, final char replaceTo) {
        return BlancoStringUtilReplace.replaceAll(source, replaceFrom,
                replaceTo);
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
     * @return �ϊ���̕�����B
     */
    public static final String replaceAll(final String source,
            final String replaceFrom, final String replaceTo) {
        return BlancoStringUtilReplace.replace(source, replaceFrom, replaceTo,
                true);
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
        return BlancoStringUtilReplace.replace(source, replaceFrom, replaceTo,
                isReplaceAll);
    }

    /**
     * �^����ꂽ������null�̏ꍇ�ɁA����0�̕�����ւƕϊ����܂��B�����Ŗ����ꍇ�ɂ́A���̂܂܂̕����񂪖߂�܂��B
     * 
     * null�𒷂�0�̕�����ɒu�����������ꍇ�ɁA���̃��\�b�h�𗘗p���܂��B<br>
     * �����񏈗��̉ߒ��� null�����肤��̂��� ����͒���0�̕�����Ƃ݂Ȃ������ꍇ�ɂ��̃��\�b�h�𗘗p���邱�Ƃ�z�肵�܂��B
     * 
     * @param originalString
     *            ���͕�����B������null��^����� ����0�̕�����ɕϊ�����܂��B
     * @return �ϊ���̕�����B�K�� null�ȊO���߂�܂��B
     */
    public static final String null2Blank(final String originalString) {
        if (originalString == null) {
            // null�̏ꍇ�ɂ́A����0�̕�����ɒu���������܂��B
            return "";
        }
        // �����łȂ��ꍇ�ɂ́A���Ƃ̕����񂪂��̂܂ܖ߂�܂��B
        return originalString;
    }

    /**
     * ������̒������擾���܂��Bnull�̏ꍇ�ɂ� ����0�Ƃ݂Ȃ��܂��B
     * 
     * null�����肤�镶����̒������擾�������ꍇ�ɗ��p����邱�Ƃ�z�肵�Ă��܂��B
     * 
     * @deprecated �������̊m�ۂ̂��߂ɁAnull2Blank��length()�𒼐ڌĂяo�����Ƃ������߂��܂��B
     * @param sourceString
     *            �������擾������������B
     * @return ������̒����Bnull���^����ꂽ�ꍇ�ɂ�0���߂�܂��B
     */
    public static final int getLengthNullable(final String sourceString) {
        // null���^�����Ă�����0�ł���Ɣ��f���܂��B
        return null2Blank(sourceString).length();
    }

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
        return BlancoStringUtilTrim.trimLeft(originalString);
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
        return BlancoStringUtilTrim.trimRight(originalString);
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
        return BlancoStringUtilTrim.trim(originalString);
    }

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
        return BlancoStringUtilPad.padRight(argSource, argLength, argPadChar);
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
        return BlancoStringUtilPad.padLeft(argSource, argLength, argPadChar);
    }

    /**
     * Windows 31J���Z�Ŏw��̒����ɂȂ�܂ŁA������̉E���ɕ�����ǉ����܂��B
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
        return BlancoStringUtilPad.padRightWindows31J(argSource, argLength,
                argPadChar);
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
        return BlancoStringUtilPad.padLeftWindows31J(argSource, argLength,
                argPadChar);
    }
}
