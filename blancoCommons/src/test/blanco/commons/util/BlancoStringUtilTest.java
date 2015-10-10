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

import junit.framework.TestCase;

/**
 * ���O�Ɋւ��郆�[�e�B���e�B�̃e�X�g�P�[�X�ł�
 * 
 * @author iga
 */
public class BlancoStringUtilTest extends TestCase {
    public void testReplaceAllString() throws Exception {
        assertEquals("BBB", BlancoStringUtil.replaceAll("AAA", "A", "B"));
        assertEquals("BBC", BlancoStringUtil.replaceAll("ABC", "A", "B"));
        assertEquals("�ׂ̋q�͗ǂ���񂲐H���q��", BlancoStringUtil.replaceAll(
                "�ׂ̋q�͗ǂ��`�H���q��", "�`", "���"));
        assertEquals("�ׂ̋q�͗ǂ��`�H���q��", BlancoStringUtil.replaceAll("�ׂ̋q�͗ǂ��`�H���q��",
                "��", "��"));
        assertEquals("���̋q�͗ǂ��`�H���q��", BlancoStringUtil.replaceAll("�ׂ̋q�͗ǂ��`�H���q��",
                "��", "��"));
        assertEquals("�ׂ̐l�͗ǂ��`�H���l��", BlancoStringUtil.replaceAll("�ׂ̋q�͗ǂ��`�H���q��",
                "�q", "�l"));
        assertEquals("�ׂ̂��Ⴍ�͗ǂ��`�H�����Ⴍ��", BlancoStringUtil.replaceAll(
                "�ׂ̋q�͗ǂ��`�H���q��", "�q", "���Ⴍ"));
        assertEquals("�ׂ̂��Ⴍ�͗ǂ��`�H�����Ⴍ��", BlancoStringUtil.replaceAll(
                "�ׂ̋q�͗ǂ��`�H���q��", "�q", "���Ⴍ"));
        assertEquals("�ׂ̂͗ǂ��`�H����", BlancoStringUtil.replaceAll("�ׂ̋q�͗ǂ��`�H���q��",
                "�q", ""));
        assertEquals("DEF", BlancoStringUtil.replaceAll("ABC", "ABC", "DEF"));
        assertEquals("ABC", BlancoStringUtil.replaceAll("ABC", "ABD", "DEF"));
        assertEquals("DEFDEF", BlancoStringUtil.replaceAll("ABCABC", "ABC",
                "DEF"));
        assertEquals("ZDEFDEFZ", BlancoStringUtil.replaceAll("ZABCABCZ", "ABC",
                "DEF"));
        assertEquals("ZDEFZDEFZ", BlancoStringUtil.replaceAll("ZABCZABCZ",
                "ABC", "DEF"));
        // �������[�v�`�F�b�N�p�B
        assertEquals("�ׂ̂��Ⴍ�q�͗ǂ��`�H�����Ⴍ�q��", BlancoStringUtil.replaceAll(
                "�ׂ̋q�͗ǂ��`�H���q��", "�q", "���Ⴍ�q"));
    }

    public void testReplaceString() throws Exception {
        assertEquals("�ׂ̂��Ⴍ�q�͗ǂ��`�H�����Ⴍ�q��", BlancoStringUtil.replace(
                "�ׂ̋q�͗ǂ��`�H���q��", "�q", "���Ⴍ�q", true));
        assertEquals("�ׂ̂��Ⴍ�q�͗ǂ��`�H���q��", BlancoStringUtil.replace("�ׂ̋q�͗ǂ��`�H���q��",
                "�q", "���Ⴍ�q", false));
    }

    public void testTrimLeft() throws Exception {
        assertEquals("ABC", BlancoStringUtil.trimLeft("ABC"));
        assertEquals("ABC", BlancoStringUtil.trimLeft(" ABC"));
        assertEquals("ABC ", BlancoStringUtil.trimLeft("ABC "));
        assertEquals("", BlancoStringUtil.trimLeft(" "));
        assertEquals("", BlancoStringUtil.trimLeft("  "));
        assertEquals("", BlancoStringUtil.trimLeft("   "));
        assertEquals("A", BlancoStringUtil.trimLeft("   A"));
    }

    public void testTrimRight() throws Exception {
        assertEquals("ABC", BlancoStringUtil.trimRight("ABC"));
        assertEquals(" ABC", BlancoStringUtil.trimRight(" ABC"));
        assertEquals("ABC", BlancoStringUtil.trimRight("ABC "));
        assertEquals("", BlancoStringUtil.trimRight(" "));
        assertEquals("", BlancoStringUtil.trimRight("  "));
        assertEquals("", BlancoStringUtil.trimRight("   "));
        assertEquals("A", BlancoStringUtil.trimRight("A   "));
    }

    public void testTrim() throws Exception {
        assertEquals("ABC", BlancoStringUtil.trim("ABC"));
        assertEquals("ABC", BlancoStringUtil.trim(" ABC"));
        assertEquals("ABC", BlancoStringUtil.trim("ABC "));
        assertEquals("", BlancoStringUtil.trim(" "));
        assertEquals("", BlancoStringUtil.trim("  "));
        assertEquals("", BlancoStringUtil.trim("   "));
        assertEquals("A", BlancoStringUtil.trim("A   "));
        assertEquals("A", BlancoStringUtil.trim("   A   "));
    }

    public void testPad() throws Exception {
        assertEquals("ABCZZ", BlancoStringUtil.padRight("ABC", 5, 'Z'));
        assertEquals("ZZZZZ", BlancoStringUtil.padRight("", 5, 'Z'));
        assertEquals("ABCDZ", BlancoStringUtil.padRight("ABCD", 5, 'Z'));
        assertEquals("ABCDE", BlancoStringUtil.padRight("ABCDE", 5, 'Z'));
        assertEquals("ABCDEF", BlancoStringUtil.padRight("ABCDEF", 5, 'Z'));

        assertEquals("ZZABC", BlancoStringUtil.padLeft("ABC", 5, 'Z'));
        assertEquals("ZZZZZ", BlancoStringUtil.padLeft("", 5, 'Z'));
        assertEquals("ZABCD", BlancoStringUtil.padLeft("ABCD", 5, 'Z'));
        assertEquals("ABCDE", BlancoStringUtil.padLeft("ABCDE", 5, 'Z'));
        assertEquals("ABCDEF", BlancoStringUtil.padLeft("ABCDEF", 5, 'Z'));
    }
    public void testPadWindows31J() throws Exception {
        assertEquals("������Z", BlancoStringUtil.padRightWindows31J("������", 7, 'Z'));
        assertEquals("��", BlancoStringUtil.padRightWindows31J("��", 1, 'Z'));
        assertEquals("��", BlancoStringUtil.padRightWindows31J("��", 2, 'Z'));
        assertEquals("��Z", BlancoStringUtil.padRightWindows31J("��", 3, 'Z'));
        assertEquals("��", BlancoStringUtil.padLeftWindows31J("��", 1, 'Z'));
        assertEquals("��", BlancoStringUtil.padLeftWindows31J("��", 2, 'Z'));
        assertEquals("Z��", BlancoStringUtil.padLeftWindows31J("��", 3, 'Z'));
        assertEquals("Z������", BlancoStringUtil.padLeftWindows31J("������", 7, 'Z'));
    }
}
