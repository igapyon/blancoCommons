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

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * blanco Framework�̖��O�ό`�Ɋւ��郆�[�e�B���e�B���W�߂��N���X�ł��B
 * 
 * @author IGA Tosiki
 */
public class BlancoNameAdjuster {
    /**
     * blanco Framework�̖��O�ό`�Ƃ��āA�������ׂ������ł��邩�ǂ������`�F�b�N���܂��B
     * 
     * Java�̃N���X���⃁�\�b�h���Ƃ��ė��p���s�K�؂ƍl�����镶���ł��邩�ǂ����𔻒f���Ă��܂��B<br>
     * ����d�l�㗘�p�\�ȕ����ł����Ă��A����blanco Framework�Ƃ��Ă͗��p�������߂Ȃ������͒��������Ƃ��Ĕ��f���܂��B
     * 
     * @param checkChar
     *            �`�F�b�N���s���������͕����B
     * @return �������ׂ������Ɣ��f�����ꍇ�ɂ�true��߂��܂��B
     */
    public static final boolean checkAdjustChar(final char checkChar) {
        switch (checkChar) {
        case '!':
        case '"':
        case '#':
        case '%':
        case '&':
        case '\'':
        case '(':
        case ')':
        case '-':
        case '=':
        case '^':
        case '~':
        case '\\':
        case '|':
        case '@':
        case '[':
        case ']':
        case '{':
        case '}':
        case ';':
        case '+':
        case ':':
        case '*':
        case '<':
        case '>':
        case ',':
        case '.':
        case '/':
        case '?':
        case '_':
        case ' ':
            // �������ׂ������ł���Ɣ��f���܂����B
            return true;
        default:
            return false;
        }
    }

    /**
     * blanco Framework�̖��O�ό`�Ƃ��āA�������ׂ������ł��邩�ǂ������`�F�b�N���܂��B
     * 
     * Java�̃N���X���⃁�\�b�h���Ƃ��ė��p���s�K�؂ƍl�����镶�����܂܂�Ă��邩�ǂ����𔻒f���Ă��܂��B<br>
     * ����d�l�㗘�p�\�ȕ����ł����Ă��A����blanco Framework�Ƃ��Ă͗��p�������߂Ȃ������͒��������Ƃ��Ĕ��f���܂��B
     * 
     * @param checkChar
     *            �`�F�b�N���s���������͕�����B
     * @return �������ׂ������Ɣ��f�����ꍇ�ɂ�true��߂��܂��B
     */
    public static final boolean checkAdjustCharExist(final String checkChar) {
        if (checkChar == null) {
            throw new IllegalArgumentException(
                    "���������񂪊܂܂�邩�ǂ������`�F�b�N���郁�\�b�h (BlancoNameAdjuster.checkAdjustCharExist)�� null���^�����܂������A���̃��\�b�h��null��^���邱�Ƃ͂ł��܂���B");
        }

        for (int index = 0; index < checkChar.length(); index++) {
            if (checkAdjustChar(checkChar.charAt(index))) {
                return true;
            }
        }
        return false;
    }

    /**
     * �^����ꂽ������̐擪�̕������啶���ɂȂ�悤�ɖ��O�ό`���܂��B
     * 
     * @param originalString
     *            ���͕�����Bnull�ȊO�̕������^���܂��B����0�̕������^���邱�Ƃ͋�����Ă��܂��B
     * @return �ό`��̕�����
     */
    public static final String toUpperCaseTitle(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "������̐擪������啶���ɕϊ����郋�[�`����null���^�����܂����B");
        }
        if (originalString.length() == 0) {
            // ����0�̕�����ɂ��ẮA����������Ă��܂��B���̂܂ܖ߂��܂��B
            return originalString;
        }

        final char[] buf = originalString.toCharArray();
        if (buf[0] != Character.toUpperCase(buf[0])) {
            // �擪�������啶���ł͖����̂ŁA�擪������啶���ɕϊ����܂��B
            buf[0] = Character.toUpperCase(buf[0]);
            // �ό`��̕������߂��܂��B
            return new String(buf);
        } else {
            // �ό`�̕K�v�������̂ŁA���͕���������̂܂ܖ߂��܂��B
            return originalString;
        }
    }

    /**
     * �^����ꂽ������̐擪�̕������������ɂȂ�悤�ɖ��O�ό`���܂��B
     * 
     * 
     * @param originalString
     *            ���͕�����Bnull�ȊO�̕������^���܂��B����0�̕������^���邱�Ƃ͋�����Ă��܂��B
     * @return �ό`��̕�����
     */
    public static final String toLowerCaseTitle(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "������̐擪�������������ɕϊ����郋�[�`����null���^�����܂����B");
        }
        if (originalString.length() == 0) {
            // ����0�̕�����ɂ��ẮA����������Ă��܂��B���̂܂ܖ߂��܂��B
            return originalString;
        }

        final char[] buf = originalString.toCharArray();
        if (buf[0] != Character.toLowerCase(buf[0])) {
            // �擪�������������ł͖����̂ŁA�擪�������������ɕϊ����܂��B
            buf[0] = Character.toLowerCase(buf[0]);
            // �ό`��̕������߂��܂��B
            return new String(buf);
        } else {
            // �ό`�̕K�v�������̂ŁA���͕���������̂܂ܖ߂��܂��B
            return originalString;
        }
    }

    /**
     * �^����ꂽ�������S�đ啶���ł���ꍇ�ɂ́A�擪�����ȊO�̕�����S�ď������ɕϊ����܂��B
     * 
     * �S�đ啶�����A�����ł͂Ȃ��đ啶�������������肩�ɂ���ē����ς��Ă��܂��B<br>
     * ���̃��\�b�h�� blanco Framework�̓�������Ăяo����邱�Ƃ�z�肵�Ă��܂��B
     * 
     * @param originalString
     *            ���͕�����B
     * @return �ό`��̕�����
     */
    public static final String weakenUpperCase(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "�����񂪑S�đ啶���ł������ꍇ�ɁA�擪�����ȊO���������ɕϊ����郋�[�`����null���^�����܂����B");
        }

        if (originalString.equals(originalString.toUpperCase())) {
            // �^����ꂽ������̑S�Ă��啶���ł��B
            // �擪�����݂̂�啶���ŁA����ȊO�͏������ɂȂ�悤�ɕϊ����܂��B
            return toUpperCaseTitle(originalString.toLowerCase());
        } else {
            // �ό`�̕K�v�������̂œ��͕���������̂܂ܖ߂��܂��B
            return originalString;
        }
    }

    /**
     * �^����ꂽ��������Ablanco Framework�ɂ����钲�����ׂ������Ɋւ��Ē��Ⴕ�ĕ����̕�����ւƕ������܂��B
     * 
     * �S�đ啶���Ƃ����������������ꍇ�ɂ́A�擪�����ȊO�͏������ɕϊ�����Ƃ��������������I�Ɋ܂܂�Ă��܂��B<br>
     * �����񕪊��̌��ʁA�z��̒��ɂ͒���0�̕����񂪊܂܂��\��������_�ɒ��ӂ��ė��p���Ă��������B
     * 
     * @param originalString
     *            ���͕�����B
     * @return ���������ŕ������ꂽ������̔z��B
     */
    public static final String[] splitByAdjustChar(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "��������������Ƃɕ�����𕪊����郁�\�b�h (BlancoNameAdjuster.splitByAdjustChar)�� null���^�����܂����B���̃��\�b�h��null��^���邱�Ƃ͂ł��܂���B");
        }

        final List<java.lang.String> result = new ArrayList<java.lang.String>();
        final char[] bufRead = originalString.toCharArray();
        final CharArrayWriter writerWork = new CharArrayWriter();
        for (int index = 0; index < bufRead.length; index++) {
            if (checkAdjustChar(bufRead[index])) {
                // �������ׂ�������������܂����B�����������s���܂��B
                writerWork.flush();
                result.add(weakenUpperCase(writerWork.toString()));
                writerWork.reset();
            } else {
                // ���������ȊO�̕����ł��B���ߍ��݂��s���܂��B
                writerWork.write(bufRead[index]);
            }
        }
        writerWork.flush();
        result.add(weakenUpperCase(writerWork.toString()));
        final String[] ret = new String[result.size()];
        return (String[]) result.toArray(ret);
    }

    /**
     * �^����ꂽ������� blanco Framework�Ƃ��Ă̖��O�ό`���[���ɏ]���đÓ��ȃN���X���ւƖ��O�ό`���܂��B
     * 
     * ���̃��\�b�h�� blanco Framework�̂Ȃ�����悭���p����郁�\�b�h�ł��B�N���X���ւƖ��O�ό`���K�v�ȋǖʂł悭���p����܂��B
     * 
     * @param originalString
     *            ���͕�����B
     * @return �N���X���Ƃ��ĕό`���ꂽ��̕�����B
     */
    public static final String toClassName(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "�N���X���ւƖ��O�ό`���郁�\�b�h (BlancoNameAdjuster.toClassName)�� null���^�����܂����B���̃��\�b�h��null��^���邱�Ƃ͂ł��܂���B");
        }

        final StringWriter result = new StringWriter();
        // ������𒲐������ŋ�؂�܂��B
        // ���������ŋ�؂�����̕������啶�������̏ꍇ�ɂ́A�擪�ȊO�͏������̕����ւƕϊ����܂��B
        final String[] work = splitByAdjustChar(originalString);
        for (int index = 0; index < work.length; index++) {
            if (work[index].length() == 0) {
                // ������0�̏ꍇ�͏������X�L�b�v���܂��B
                continue;
            }
            // ���������ŋ�؂�ꂽ���Ƃ̕�����ɂ��āA�擪������啶���ւƕό`���܂��B
            result.write(toUpperCaseTitle(work[index]));
        }
        try {
            result.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "��������N���X���ɒ������郋�[�`���ɂ����āA��������͂��̂Ȃ���O���������܂����B:" + e.toString());
        }
        return result.toString();
    }

    /**
     * �^����ꂽ������� blanco Framework�Ƃ��Ă̖��O�ό`���[���ɏ]���đÓ��ȃp�����[�^���ւƖ��O�ό`���܂��B
     * 
     * �����I�ɂ́AtoClassName���Ăяo������� �擪�������������ւƕό`�����Ă��܂��B
     * 
     * @param originalString
     *            ���͕�����B
     * @return �p�����[�^���Ƃ��ĕό`���ꂽ��̕�����B
     */
    public static final String toParameterName(final String originalString) {
        if (originalString == null) {
            throw new IllegalArgumentException(
                    "�p�����[�^���ւƖ��O�ό`���郁�\�b�h (BlancoNameAdjuster.toParameterName)�� null���^�����܂����B���̃��\�b�h��null��^���邱�Ƃ͂ł��܂���B");
        }

        // �N���X���ւƖ��O�ό`��������ɐ擪��������������ւƕό`�����܂��B
        return toLowerCaseTitle(toClassName(originalString));
    }
}
