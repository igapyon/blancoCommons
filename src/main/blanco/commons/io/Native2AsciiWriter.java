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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import blanco.commons.util.BlancoStringUtil;

/**
 * Native2Ascii���C�^�[
 * 
 * java.util.Properties�̗͂��g���� Native2Ascii���s���܂��B
 * 
 * @author IGA Tosiki
 * @see java.util.Properties#load(java.io.InputStream)
 */
public class Native2AsciiWriter {
    private BufferedWriter fWriter;

    /**
     * ��{�I�ɂ́A8859_1�G���R�[�h�̃��C�^�[��n���Ă��������B
     * 
     * @param writer
     *            �o�͐惉�C�^�[�B
     */
    public Native2AsciiWriter(final BufferedWriter writer) {
        this.fWriter = writer;
    }

    /**
     * ���C�^�[���t���b�V�����܂��B
     * 
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    public void flush() throws IOException {
        fWriter.flush();
    }

    /**
     * ���C�^�[���N���[�Y���܂��B
     * 
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    public void close() throws IOException {
        fWriter.close();
    }

    /**
     * ���C�^�[�ɃR�����g���o�͂��܂��B<br>
     * �X�y�[�X���~�����ꍇ�ɂ́A�^���镶����̐擪�ɃX�y�[�X�������Ă��������B
     * 
     * @param comment
     * @throws IOException
     */
    public void writeComment(final String comment) throws IOException {
        fWriter.write("#" + encodeNative2AsciiComment(comment));
        fWriter.newLine();
    }

    /**
     * ���C�^�[�Ƀv���p�e�B���o�͂��܂��B
     * 
     * @param key
     *            �L�[�B
     * @param value
     *            �l�B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    public void writeProperty(final String key, final String value)
            throws IOException {
        fWriter.write(encodeNative2AsciiKey(key));
        fWriter.write("=");
        fWriter.write(encodeNative2AsciiValue(value));
        fWriter.newLine();
    }

    /**
     * �^����ꂽ�l�C�e�B�u����������z�v���p�e�B�t�@�C����byte�z��ɕϊ����܂��B
     * 
     * @param nativeString
     *            �l�C�e�B�u������B
     * @return �o�C�g�z��B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    private static final byte[] encodeNative2AsciiKeyByteArray(
            final String nativeString) throws IOException {
        final Properties prop = new Properties();
        prop.setProperty(nativeString, "value");
        final ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        try {
            prop.store(byteOutStream, "dummy");
            byteOutStream.flush();
        } finally {
            byteOutStream.close();
        }
        return byteOutStream.toByteArray();
    }

    /**
     * �^����ꂽ�l�C�e�B�u����������z�v���p�e�B�t�@�C����byte�z��ɕϊ����܂��B
     * 
     * @param nativeString
     *            �ϊ����Ƃ̕�����B
     * @return �ϊ���̃o�C�g�z��B
     * @throws IOException
     *             ���o�͗�O�����������ꍇ�B
     */
    private static final byte[] encodeNative2AsciiValueByteArray(
            final String nativeString) throws IOException {
        final Properties prop = new Properties();
        prop.setProperty("key", nativeString);
        final ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        try {
            prop.store(byteOutStream, "dummy");
            byteOutStream.flush();
        } finally {
            byteOutStream.close();
        }
        return byteOutStream.toByteArray();
    }

    /**
     * native2ascii�����s���܂��B
     * 
     * �ڂ�����
     * http://java.sun.com/j2se/1.4/ja/docs/ja/api/java/util/Properties.html
     * #load(java.io.InputStream) ���Q�Ƃ��������B
     * 
     * @param nativeString
     *            �ϊ����Ƃ̕�����B
     * @return �ϊ���̕�����B
     * @see java.util.Properties#load(java.io.InputStream)
     */
    public static final String encodeNative2AsciiKey(final String nativeString) {
        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(
                            encodeNative2AsciiKeyByteArray(nativeString)),
                            "8859_1"));
            for (;;) {
                final String look = reader.readLine();
                if (look == null) {
                    throw new IllegalArgumentException(
                            "encodeNative2Ascii("
                                    + nativeString
                                    + ")�̏����̉ߒ��ɂ����āA���ԃ������t�@�C���̗\�����ʏI�[���������܂����B���̃P�[�X�͂��肦�܂���B");
                }
                if (look.startsWith("#")) {
                    // �R�����g�s�͓ǂݔ�΂��܂��B
                    continue;
                }
                reader.close();
                if (look.endsWith("=value")) {
                    return look.substring(0, look.lastIndexOf("=value"));
                }
                throw new IllegalArgumentException("encodeNative2AsciiKey("
                        + nativeString + ")�̏����̉ߒ��ɂ����āA���ԃ������t�@�C���̓ǂݍ��݂̌��ʁA�\�����ʍs["
                        + look + "]���߂�܂����B���̃P�[�X�͂��肦�܂���B");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("encodeNative2Ascii("
                    + nativeString + ")�̏����̉ߒ��ɂ����āA�\�����ʗ�O���������܂����B���̃P�[�X�͂��肦�܂���B:"
                    + ex.toString());
        }
    }

    /**
     * native2ascii�����s���܂��B
     * 
     * �ڂ�����
     * http://java.sun.com/j2se/1.4/ja/docs/ja/api/java/util/Properties.html
     * #load(java.io.InputStream) ���Q�Ƃ��Ă��������B
     * 
     * @param nativeString
     *            �ϊ����Ƃ̕�����B
     * @return �ϊ���̕�����B
     * @see java.util.Properties#load(java.io.InputStream)
     */
    public static final String encodeNative2AsciiValue(final String nativeString) {
        try {
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(
                            encodeNative2AsciiValueByteArray(nativeString)),
                            "8859_1"));
            for (;;) {
                final String look = reader.readLine();
                if (look == null) {
                    throw new IllegalArgumentException(
                            "encodeNative2Ascii("
                                    + nativeString
                                    + ")�̏����̉ߒ��ɂ����āA���ԃ������t�@�C���̗\�����ʏI�[���������܂����B���̃P�[�X�͂��肦�܂���B");
                }
                if (look.startsWith("#")) {
                    // �R�����g�s�͓ǂݔ�΂��܂��B
                    continue;
                }
                reader.close();
                if (look.startsWith("key=")) {
                    return look.substring("key=".length());
                }
                throw new IllegalArgumentException("encodeNative2Ascii("
                        + nativeString + ")�̏����̉ߒ��ɂ����āA���ԃ������t�@�C���̓ǂݍ��݂̌��ʁA�\�����ʍs["
                        + look + "]���߂�܂����B���̃P�[�X�͂��肦�܂���B");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("encodeNative2Ascii("
                    + nativeString + ")�̏����̉ߒ��ɂ����āA�\�����ʗ�O���������܂����B���̃P�[�X�͂��肦�܂���B:"
                    + ex.toString());
        }
    }

    /**
     * �^����ꂽ�����񂪃R�����g�ł�����̂Ƃ���Native2Ascii�����s���܂��B
     * 
     * @param nativeString
     *            �ϊ����s�������Ώۂ̕�����B
     * @return ascii�R�����g�Ƃ��ĕϊ���̕�����B
     */
    public static final String encodeNative2AsciiComment(
            final String nativeString) {
        final StringReader reader = new StringReader(nativeString);
        final StringWriter writer = new StringWriter();
        try {
            for (;;) {
                final int iRead = reader.read();
                if (iRead < 0) {
                    break;
                }
                if ((iRead < 0x0020) || (iRead > 0x007E)) {
                    // �������������̂� \u007E�����傫�����̂�
                    // \\uxxxx�̂悤��16�i�\�����s���܂��B
                    writer.write(toHexString((char) iRead));
                } else {
                    // ���������A���̂܂܏����o���܂��B
                    writer.write((char) iRead);
                }
            }
            writer.flush();
            return writer.toString();
        } catch (IOException e) {
            // �܂�����͂��肦�܂���B
            e.printStackTrace();
            return null;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // �܂�����͂��肦�܂���B
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * �^����ꂽchar������Unicode�\�L�ɕϊ����܂��B<br>
     * native2ascii.exe�̎d�l�Ƃ͈قȂ�܂����Ajava.util.Properties�̎d�l�ɏ]���A�啶���ɕϊ����܂��B<br>
     * ���̃��[�`���́ANative2AsciiWriter�̃R�����g�G���R�[�h�ɗ��p����܂��B
     * 
     * @param arg
     *            HEX�\�L�������Ȃ�����������B
     * @return HEX�\�L�ɕϊ���̕�����B
     */
    private static final String toHexString(final char arg) {
        return "\\u" + BlancoStringUtil.toHexString(arg).toUpperCase();
    }
}
