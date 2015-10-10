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
import java.util.ArrayList;
import java.util.List;

/**
 * blanco Framework�̖��O�Ɋւ��郆�[�e�B���e�B���W�߂��N���X�ł��B
 * 
 * @author IGA Tosiki
 */
public class BlancoNameUtil {
    /**
     * �^����ꂽ���O���t�@�C�����Ƃ��ė��p���Ó����ǂ����𔻒f���܂��B
     * 
     * �^���F�G���H�h�����b���^����ꂽ��false��߂��܂��B
     * 
     * @param checkString
     *            �t�@�C�����Ƃ��Ĕ��肷�镶����B
     * @return �t�@�C�����Ƃ��đÓ��ł����true���A�Ó��łȂ���� false��߂��܂��B
     */
    public static final boolean isValidFileName(final String checkString) {
        if (checkString == null) {
            throw new IllegalArgumentException(
                    "�t�@�C�����Ƃ��đÓ����ǂ������肷�郁�\�b�h��null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }

        if (checkString.indexOf('/') >= 0 || checkString.indexOf('\\') >= 0
                || checkString.indexOf(':') >= 0
                || checkString.indexOf(';') >= 0
                || checkString.indexOf('*') >= 0
                || checkString.indexOf('?') >= 0
                || checkString.indexOf('\"') >= 0
                || checkString.indexOf('>') >= 0
                || checkString.indexOf('<') >= 0
                || checkString.indexOf('|') >= 0) {
            // �����̕������܂܂�Ă�����t�@�C�����Ƃ��Ă͕s�K�؂ł��B
            return false;
        }
        return true;
    }

    /**
     * �t�@�C�����Ƃ��ė��p���ׂ��łȂ������̈ꗗ��\���`���Ŏ擾���܂��B
     * 
     * @return ���p���ׂ��łȂ�������̈ꗗ��\���`���ł���킵�����́B
     */
    public static final String invalidFileNameChar() {
        return "/ \\ : ; * ? \" > < |";
    }

    /**
     * �t�@�C����(�f�B���N�g�����͊܂܂Ȃ���Ԃŗ^���܂�)����g���q���������܂��B
     * 
     * �h�b�g�͊g���q�Ɠ�������������܂��B<br>
     * ���� .cvsignore�̂悤�ȃt�@�C�������^����ꂽ�ꍇ�ɂ́A���̂܂ܖ߂�܂��B
     * 
     * @param checkString
     *            �t�@�C����(�f�B���N�g�����܂܂Ȃ�����)
     * @return �g���q���܂܂Ȃ��t�@�C����
     */
    public static final String trimFileExtension(final String checkString) {
        if (checkString == null) {
            throw new IllegalArgumentException(
                    "�t�@�C��������g���q����菜�����\�b�h��null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }

        // �Ō�̊g���q��T���o���܂��B
        final int posDot = checkString.lastIndexOf(".");
        if (posDot == 0) {
            // ���̃t�@�C�����̓h�b�g����J�n�����t�@�C�����ł��B
            // �g���q�̐؂���͍s�킸�ɁA���̂܂ܕԂ��܂��B
            return checkString;
        }
        if (posDot > 0) {
            // �g���q���������܂��B
            return checkString.substring(0, posDot);
        }

        return checkString;
    }

    /**
     * �^����ꂽ��������w�肳�ꂽ��؂蕶���ŕ������܂��B
     * 
     * ��؂蕶���� '/'�ł���ꍇ�̋����͉��L�̒ʂ�B<br>
     * �ʏ�P�[�X1�F[aaa/bbb/ccc]��{ "aaa", "bbb", "ccc" }<br>
     * �ʏ�P�[�X2�F[aaa//ccc]��{ "aaa", "", "ccc" }<br>
     * �ʏ�P�[�X3: [/aaa/bbb]��{ "", "aaa", "bbb" }<br>
     * ���ŏ��̃X���b�V���͈�Ƃ��ăJ�E���g���܂��B<br>
     * �ʏ�P�[�X4�F[/aaa/bbb/]��{ "", "aaa", "bbb" }<br>
     * ���Ō�̃X���b�V���͖������܂��B<br>
     * <br>
     * ���E�P�[�X1�F[/]��{ "" }<br>
     * ���E�P�[�X2�F[///]��{ "", "", "" }<br>
     * ���E�P�[�X3�F[]������0�̔z��<br>
     * ���E�P�[�X4�Fnull������0�̔z��<br>
     * 
     * @param originalString
     *            ��؂蕶���ŋ�؂�ꂽ������Bnull��^�����ꍇ�ɂ� ����0��String�z�񂪖߂�܂��B
     * @return �������ꂽ������B��؂蕶�����̂��̂͊܂܂�܂���B
     */
    public static final String[] splitString(final String originalString,
            final char delimiter) {
        if (originalString == null) {
            // null�Ɋւ��ẮA������ڂ��Ȃ����̂Ƃ��Ĉ����܂��B
            return new String[0];
        }

        final StringReader reader = new StringReader(originalString);
        StringWriter writer = null;
        final List<java.lang.String> result = new ArrayList<java.lang.String>();
        for (;;) {
            final int read;
            try {
                read = reader.read();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalArgumentException(
                        "���[�N��StringReader����̓ǂݍ��݂ŗ�O���������܂����B�S���z�肵�Ȃ��P�[�X�ł��B:"
                                + e.toString());
            }
            if (read < 0) {
                break;
            }
            if (writer == null) {
                writer = new StringWriter();
            }
            if ((char) read == delimiter) {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new IllegalArgumentException(
                                "���[�N��StringWriter�̃N���[�Y�ŗ�O���������܂����B�S���z�肵�Ȃ��P�[�X�ł��B:"
                                        + e.toString());
                    }
                    result.add(writer.toString());
                    // ���[�N�ϐ��̒��g�����Z�b�g���܂��B
                    writer = null;
                }
            } else {
                writer.write((char) read);
            }
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new IllegalArgumentException(
                        "�S���z�肵�Ȃ��A���肦�Ȃ��P�[�X�ł̃G���[���������܂����B:" + e.toString());
            }
            result.add(writer.toString());
            // ���[�N�ϐ��̒��g�����Z�b�g���܂��B
            writer = null;
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    /**
     * �^����ꂽ��������p�X�ł���Ɖ��肵�A/(�X���b�V��)�L���ŋ�؂��ĕ�����𕪊����܂��B
     * 
     * �ʏ�P�[�X1�F[aaa/bbb/ccc]��{ "aaa", "bbb", "ccc" }<br>
     * �ʏ�P�[�X2�F[aaa//ccc]��{ "aaa", "", "ccc" }<br>
     * �ʏ�P�[�X3: [/aaa/bbb]��{ "", "aaa", "bbb" }<br>
     * ���ŏ��̃X���b�V���͈�Ƃ��ăJ�E���g���܂��B<br>
     * �ʏ�P�[�X4�F[/aaa/bbb/]��{ "", "aaa", "bbb" }<br>
     * ���Ō�̃X���b�V���͖������܂��B<br>
     * <br>
     * ���E�P�[�X1�F[/]��{ "" }<br>
     * ���E�P�[�X2�F[///]��{ "", "", "" }<br>
     * ���E�P�[�X3�F[]������0�̔z��<br>
     * ���E�P�[�X4�Fnull������0�̔z��<br>
     * 
     * @param originalString
     *            /(�X���b�V��)�L���ŋ�؂�ꂽ������Bnull��^�����ꍇ�ɂ� ����0��String�z�񂪖߂�܂��B
     * @return �������ꂽ������B/(�X���b�V��)�L���͊܂܂�܂���B
     */
    public static final String[] splitPath(final String originalString) {
        return splitString(originalString, '/');
    }

    /**
     * �^����ꂽ�N���X������p�b�P�[�W�����������܂��B
     * 
     * @param argClassName
     *            �t���p�b�P�[�W���t���̃N���X���B
     * @return �p�b�P�[�W�������̃N���X���B
     */
    public static final String trimJavaPackage(final String argClassName) {
        if (argClassName == null) {
            throw new IllegalArgumentException(
                    "�N���X������p�b�P�[�W������菜�����\�b�h��null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }

        // �h�b�g�Ńt���N���X���𕪊����܂��B
        final String[] work = splitString(argClassName, '.');
        for (int index = work.length - 1; index >= 0; index--) {
            if (work[index].length() > 0) {
                // ������̕���������Ă����A�������������Ō�̕�����������ăN���X���Ƃ��܂��B
                return work[index];
            }
        }

        // �ЂƂ����̂����������O��������܂���ł����B
        throw new IllegalArgumentException("������[" + argClassName
                + "]���p�b�P�[�W���t��Java�N���X���Ƃ��ď����ł��܂���B");
    }

    /**
     * �^����ꂽURI����A���閽���K���ɏ]���� Java�p�b�P�[�W�����擾���܂��B
     * 
     * @param uri
     *            ���͂ƂȂ�URI�B
     * @return Java�p�b�P�[�W���B
     */
    public static final String uri2JavaPackage(final String uri) {
        if (uri == null) {
            throw new IllegalArgumentException(
                    "URI����p�b�P�[�W�����擾���郁�\�b�h��null���^�����܂����Bnull�ȊO�̒l��^���Ă��������B");
        }

        final StringWriter writer = new StringWriter();
        final String[] splitedUri = splitString(uri, ':');
        boolean isFirst = true;
        if (splitedUri.length < 2) {
            throw new IllegalArgumentException(
                    "�^����ꂽURI["
                            + uri
                            + "]�͌`�����s���ł��B[http://www.w3.org/XML/Schema]�̂悤�Ȍ`���Ŏw�肵�Ă��������B");
        }
        final String[] splitDir = BlancoNameUtil
                .splitString(splitedUri[1], '/');
        for (int index = 0; index < splitDir.length; index++) {
            final String[] splitDomain = BlancoNameUtil.splitString(
                    splitDir[index], '.');
            for (int indexDomain = splitDomain.length - 1; indexDomain >= 0; indexDomain--) {
                // �h���C����\���Ǝv����ӏ��ł���̂ŁA�t���ŏ������s���܂��B
                if (splitDomain[indexDomain].length() > 0) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        writer.write(".");
                    }
                    writer.write(splitDomain[indexDomain]);
                }
            }
        }
        for (int indexUri = 2; indexUri < splitedUri.length; indexUri++) {
            final String[] splitDirAfter = BlancoNameUtil.splitString(
                    splitedUri[indexUri], '/');
            // �|�C���g�F�ŏ��̃m�[�h�� �|�[�g�ԍ��ł��낤���� ����𖳎������܂��B
            for (int index = 1; index < splitDirAfter.length; index++) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    writer.write(".");
                }
                writer.write(splitDirAfter[index]);
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "���肦�Ȃ���O: StringWriter��close()�̍ۂɗ�O���������܂����B:"
                            + e.toString());
        }
        return writer.toString();
    }
}
