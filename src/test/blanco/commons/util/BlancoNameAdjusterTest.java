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
 * ���O�����Ɋւ��郆�[�e�B���e�B�̃e�X�g�P�[�X�ł�
 * 
 * @author iga
 */
public class BlancoNameAdjusterTest extends TestCase {
    /**
     * �N���X���ό`�̎���<br>
     * 
     * �������FtoUpper���ĕω����镶�� �啶���F�������ȊO�̕���
     * 
     * �啶���̂� COLUMNNAME �� Columnname (Oracle�̗񖼂��S�đ啶��)<br>
     * �������啶�����藐�� ColumnName �� ColumnName (�擪��啶���A�ω��Ȃ�)<br>
     * columnName �� ColumnName (�擪��啶��)<br>
     * �������̂� columnname �� Columnname<br>
     * 
     * �A���_�[�o�[�Ȃǂ̓g�[�N�������ƂȂ�<br>
     * abc_def �� AbcDef<br>
     * 
     * 
     * @throws Exception
     */
    public void testToClassName() throws Exception {
        // 1����
        assertEquals("A", BlancoNameAdjuster.toClassName("a"));
        assertEquals("A", BlancoNameAdjuster.toClassName("A"));
        assertEquals("1", BlancoNameAdjuster.toClassName("1"));
        assertEquals("��", BlancoNameAdjuster.toClassName("��"));

        // 1�����A�A���_�[�o�[�擪
        assertEquals("A", BlancoNameAdjuster.toClassName("_a"));
        assertEquals("A", BlancoNameAdjuster.toClassName("_A"));
        assertEquals("1", BlancoNameAdjuster.toClassName("_1"));
        assertEquals("��", BlancoNameAdjuster.toClassName("_��"));

        // 1�����A�A���_�[�o�[����
        assertEquals("A", BlancoNameAdjuster.toClassName("a_"));
        assertEquals("A", BlancoNameAdjuster.toClassName("A_"));
        assertEquals("1", BlancoNameAdjuster.toClassName("1_"));
        assertEquals("��", BlancoNameAdjuster.toClassName("��_"));

        // 1�����A�A���_�[�o�[����
        assertEquals("AA", BlancoNameAdjuster.toClassName("a_a"));
        assertEquals("AA", BlancoNameAdjuster.toClassName("a_A"));
        assertEquals("A1", BlancoNameAdjuster.toClassName("a_1"));
        assertEquals("A��", BlancoNameAdjuster.toClassName("a_��"));

        assertEquals("AA", BlancoNameAdjuster.toClassName("A_a"));
        assertEquals("AA", BlancoNameAdjuster.toClassName("A_A"));
        assertEquals("A1", BlancoNameAdjuster.toClassName("A_1"));
        assertEquals("A��", BlancoNameAdjuster.toClassName("A_��"));

        assertEquals("1A", BlancoNameAdjuster.toClassName("1_a"));
        assertEquals("1A", BlancoNameAdjuster.toClassName("1_A"));
        assertEquals("11", BlancoNameAdjuster.toClassName("1_1"));
        assertEquals("1��", BlancoNameAdjuster.toClassName("1_��"));

        assertEquals("��A", BlancoNameAdjuster.toClassName("��_a"));
        assertEquals("��A", BlancoNameAdjuster.toClassName("��_A"));
        assertEquals("��1", BlancoNameAdjuster.toClassName("��_1"));
        assertEquals("����", BlancoNameAdjuster.toClassName("��_��"));

        // �Q����
        assertEquals("Aa", BlancoNameAdjuster.toClassName("aa"));
        assertEquals("AA", BlancoNameAdjuster.toClassName("aA"));
        assertEquals("A1", BlancoNameAdjuster.toClassName("a1"));
        assertEquals("A��", BlancoNameAdjuster.toClassName("a��"));

        assertEquals("Aa", BlancoNameAdjuster.toClassName("Aa"));
        assertEquals("Aa", BlancoNameAdjuster.toClassName("AA"));
        assertEquals("A1", BlancoNameAdjuster.toClassName("A1"));
        assertEquals("A��", BlancoNameAdjuster.toClassName("A��"));

        assertEquals("1a", BlancoNameAdjuster.toClassName("1a"));
        assertEquals("1a", BlancoNameAdjuster.toClassName("1A"));
        assertEquals("11", BlancoNameAdjuster.toClassName("11"));
        assertEquals("1��", BlancoNameAdjuster.toClassName("1��"));

        assertEquals("��a", BlancoNameAdjuster.toClassName("��a"));
        assertEquals("��a", BlancoNameAdjuster.toClassName("��A"));
        assertEquals("��1", BlancoNameAdjuster.toClassName("��1"));
        assertEquals("����", BlancoNameAdjuster.toClassName("����"));

        // �R����
        assertEquals("Aaa", BlancoNameAdjuster.toClassName("aaa"));
        assertEquals("AaA", BlancoNameAdjuster.toClassName("aaA"));
        assertEquals("Aa1", BlancoNameAdjuster.toClassName("aa1"));
        assertEquals("Aa��", BlancoNameAdjuster.toClassName("aa��"));
        assertEquals("AAa", BlancoNameAdjuster.toClassName("aAa"));
        assertEquals("AAA", BlancoNameAdjuster.toClassName("aAA"));
        assertEquals("AA1", BlancoNameAdjuster.toClassName("aA1"));
        assertEquals("AA��", BlancoNameAdjuster.toClassName("aA��"));
        assertEquals("A1a", BlancoNameAdjuster.toClassName("a1a"));
        assertEquals("A1A", BlancoNameAdjuster.toClassName("a1A"));
        assertEquals("A11", BlancoNameAdjuster.toClassName("a11"));
        assertEquals("A1��", BlancoNameAdjuster.toClassName("a1��"));
        assertEquals("A��a", BlancoNameAdjuster.toClassName("a��a"));
        assertEquals("A��A", BlancoNameAdjuster.toClassName("a��A"));
        assertEquals("A��1", BlancoNameAdjuster.toClassName("a��1"));
        assertEquals("A����", BlancoNameAdjuster.toClassName("a����"));

        assertEquals("Aaa", BlancoNameAdjuster.toClassName("Aaa"));
        assertEquals("AaA", BlancoNameAdjuster.toClassName("AaA"));
        assertEquals("Aa1", BlancoNameAdjuster.toClassName("Aa1"));
        assertEquals("Aa��", BlancoNameAdjuster.toClassName("Aa��"));
        assertEquals("AAa", BlancoNameAdjuster.toClassName("AAa"));
        assertEquals("Aaa", BlancoNameAdjuster.toClassName("AAA"));
        assertEquals("Aa1", BlancoNameAdjuster.toClassName("AA1"));
        assertEquals("Aa��", BlancoNameAdjuster.toClassName("AA��"));
        assertEquals("A1a", BlancoNameAdjuster.toClassName("A1a"));
        assertEquals("A1a", BlancoNameAdjuster.toClassName("A1A"));
        assertEquals("A11", BlancoNameAdjuster.toClassName("A11"));
        assertEquals("A1��", BlancoNameAdjuster.toClassName("A1��"));
        assertEquals("A��a", BlancoNameAdjuster.toClassName("A��a"));
        assertEquals("A��a", BlancoNameAdjuster.toClassName("A��A"));
        assertEquals("A��1", BlancoNameAdjuster.toClassName("A��1"));
        assertEquals("A����", BlancoNameAdjuster.toClassName("A����"));

        assertEquals("1aa", BlancoNameAdjuster.toClassName("1aa"));
        assertEquals("1aA", BlancoNameAdjuster.toClassName("1aA"));
        assertEquals("1a1", BlancoNameAdjuster.toClassName("1a1"));
        assertEquals("1a��", BlancoNameAdjuster.toClassName("1a��"));
        assertEquals("1Aa", BlancoNameAdjuster.toClassName("1Aa"));
        assertEquals("1aa", BlancoNameAdjuster.toClassName("1AA"));
        assertEquals("1a1", BlancoNameAdjuster.toClassName("1A1"));
        assertEquals("1a��", BlancoNameAdjuster.toClassName("1A��"));
        assertEquals("11a", BlancoNameAdjuster.toClassName("11a"));
        assertEquals("11a", BlancoNameAdjuster.toClassName("11A"));
        assertEquals("111", BlancoNameAdjuster.toClassName("111"));
        assertEquals("11��", BlancoNameAdjuster.toClassName("11��"));
        assertEquals("1��a", BlancoNameAdjuster.toClassName("1��a"));
        assertEquals("1��a", BlancoNameAdjuster.toClassName("1��A"));
        assertEquals("1��1", BlancoNameAdjuster.toClassName("1��1"));
        assertEquals("1����", BlancoNameAdjuster.toClassName("1����"));

        assertEquals("��aa", BlancoNameAdjuster.toClassName("��aa"));
        assertEquals("��aA", BlancoNameAdjuster.toClassName("��aA"));
        assertEquals("��a1", BlancoNameAdjuster.toClassName("��a1"));
        assertEquals("��a��", BlancoNameAdjuster.toClassName("��a��"));
        assertEquals("��Aa", BlancoNameAdjuster.toClassName("��Aa"));
        assertEquals("��aa", BlancoNameAdjuster.toClassName("��AA"));
        assertEquals("��a1", BlancoNameAdjuster.toClassName("��A1"));
        assertEquals("��a��", BlancoNameAdjuster.toClassName("��A��"));
        assertEquals("��1a", BlancoNameAdjuster.toClassName("��1a"));
        assertEquals("��1a", BlancoNameAdjuster.toClassName("��1A"));
        assertEquals("��11", BlancoNameAdjuster.toClassName("��11"));
        assertEquals("��1��", BlancoNameAdjuster.toClassName("��1��"));
        assertEquals("����a", BlancoNameAdjuster.toClassName("����a"));
        assertEquals("����a", BlancoNameAdjuster.toClassName("����A"));
        assertEquals("����1", BlancoNameAdjuster.toClassName("����1"));
        assertEquals("������", BlancoNameAdjuster.toClassName("������"));

        // ��΂����Ȃ̂́A�ꕶ���n
        assertEquals("ABC", BlancoNameAdjuster.toClassName("A_b_c"));
        assertEquals("ABC", BlancoNameAdjuster.toClassName("a_B_c"));
        assertEquals("ABC", BlancoNameAdjuster.toClassName("a_b_C"));
        assertEquals("ABC", BlancoNameAdjuster.toClassName("A_B_c"));
        assertEquals("ABC", BlancoNameAdjuster.toClassName("a_B_C"));
        assertEquals("ABC", BlancoNameAdjuster.toClassName("A_b_C"));
        assertEquals("ABC", BlancoNameAdjuster.toClassName("A_B_C"));

        // �g�[�N��
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("aaa_bbb"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("_aaa_bbb"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("aaa_bbb_"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("_aaa_bbb_"));

        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("aaa__bbb"));

        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("__aaa_bbb"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("_aaa__bbb"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("__aaa__bbb"));

        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("aaa__bbb_"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("aaa_bbb__"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("aaa__bbb__"));

        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("__aaa_bbb_"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("_aaa__bbb_"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("_aaa_bbb__"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("__aaa__bbb_"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("__aaa_bbb__"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("_aaa__bbb__"));
        assertEquals("AaaBbb", BlancoNameAdjuster.toClassName("__aaa__bbb__"));

        // �܂񂪂����R�����Ȃǂ��܂܂ꂽ�ꍇ�A�A���_�[�o�[�Ɠ��������Ƃ���
        assertEquals("AbCDef", BlancoNameAdjuster.toClassName("abC:DEF"));
        assertEquals("AbCDef", BlancoNameAdjuster.toClassName("abC DEF"));

        // �����_������
        assertEquals("Abc", BlancoNameAdjuster.toClassName("abc"));
        assertEquals("AbcD", BlancoNameAdjuster.toClassName("abcD"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("abcDef"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("abc_def"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("abc_def "));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("_abc_def "));
        assertEquals("Abcdef", BlancoNameAdjuster.toClassName("ABCDEF"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("ABC_DEF"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("_ABC_DEF"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("_ABC DEF"));
        assertEquals("SetName", BlancoNameAdjuster.toClassName("set_NAME"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("abc_DEF"));
        assertEquals("AbCDef", BlancoNameAdjuster.toClassName("abC_DEF"));
        assertEquals("ABcDEF", BlancoNameAdjuster.toClassName("ABcDEF"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("AbcDef"));
        assertEquals("AbcDef", BlancoNameAdjuster.toClassName("Abc_def"));
        assertEquals("AbCDEF", BlancoNameAdjuster.toClassName("abCDEF"));

    }

    public void testSplitByAdjustChar() throws Exception {
        assertEquals(2, BlancoNameAdjuster.splitByAdjustChar("aa bb").length);
    }

    /**
     * �p�����[�^�ό`�̎���
     * 
     * @throws Exception
     */
    public void testParameterName() throws Exception {
        assertEquals("name", BlancoNameAdjuster.toParameterName("name"));
        assertEquals("name", BlancoNameAdjuster.toParameterName("NAME"));
        assertEquals("name", BlancoNameAdjuster.toParameterName("Name"));
        assertEquals("fileName", BlancoNameAdjuster.toParameterName("FileName"));
        assertEquals("fileName", BlancoNameAdjuster
                .toParameterName("FILE_NAME"));
        assertEquals("fileName", BlancoNameAdjuster
                .toParameterName("FILE__NAME"));
        assertEquals("fileName", BlancoNameAdjuster
                .toParameterName("__FILE__NAME"));
        assertEquals("fileName", BlancoNameAdjuster
                .toParameterName("__FILE__NAME"));
        assertEquals("setName", BlancoNameAdjuster.toParameterName("set_NAME"));
    }
}
