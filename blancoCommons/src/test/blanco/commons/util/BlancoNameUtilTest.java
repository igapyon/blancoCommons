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
public class BlancoNameUtilTest extends TestCase {
    public void testTrimFileExtention() throws Exception {
        assertEquals("abc", BlancoNameUtil.trimFileExtension("abc.txt"));
        assertEquals("abc.def", BlancoNameUtil.trimFileExtension("abc.def.txt"));
        assertEquals(".cvsignore", BlancoNameUtil
                .trimFileExtension(".cvsignore"));
        assertEquals(".cvsignore", BlancoNameUtil
                .trimFileExtension(".cvsignore.txt"));
        assertEquals(".", BlancoNameUtil.trimFileExtension(".."));
        assertEquals("aaa", BlancoNameUtil.trimFileExtension("aaa."));
        assertEquals("aaa.", BlancoNameUtil.trimFileExtension("aaa.."));
    }

    public void testSplitPath() throws Exception {
        // ��ʓI�Ȓl�̎���
        assertEqualsStringArray(new String[] { "aaa" }, BlancoNameUtil
                .splitPath("aaa"));
        assertEqualsStringArray(new String[] { "aaa", "bbb", "ccc" },
                BlancoNameUtil.splitPath("aaa/bbb/ccc"));
        assertEqualsStringArray(new String[] { "aaa" }, BlancoNameUtil
                .splitPath("aaa"));

        // ��⋫�E�l�I�Ȏ���
        assertEqualsStringArray(new String[] { "aaa", "", "ccc" },
                BlancoNameUtil.splitPath("aaa//ccc"));
        // �|�C���g�F���[�g�̓��[�g�Ƃ��ăJ�E���g�A�b�v����܂��B
        assertEqualsStringArray(new String[] { "", "aaa", "bbb" },
                BlancoNameUtil.splitPath("/aaa/bbb"));
        // �|�C���g�F�Ō�̃o�b�N�X���b�V���͖�������܂��B
        assertEqualsStringArray(new String[] { "", "aaa", "bbb" },
                BlancoNameUtil.splitPath("/aaa/bbb/"));

        // ��������͓���ȃP�[�X
        assertEqualsStringArray(new String[] { "", "", "" }, BlancoNameUtil
                .splitPath("///"));
        assertEqualsStringArray(new String[] { "", "" }, BlancoNameUtil
                .splitPath("//"));
        assertEqualsStringArray(new String[] { "" }, BlancoNameUtil
                .splitPath("/"));

        // ��������͋��E�̎���
        // �|�C���g�F"" �͖������̂Ƃ��ď�������܂��B
        assertEqualsStringArray(new String[0], BlancoNameUtil.splitPath(""));
        // �|�C���g�Fnull�� �������̂Ƃ��ď�������܂��B
        assertEqualsStringArray(new String[0], BlancoNameUtil.splitPath(null));
    }

    /**
     * URI���p�b�P�[�W���ɕό`����̂��������܂��B
     * 
     * @throws Exception
     */
    public void testUri2JavaPackage() throws Exception {
        assertEquals("ccc.bbb.aaa.ddd.eee", BlancoNameUtil
                .uri2JavaPackage("http://aaa.bbb.ccc/ddd/eee"));
        assertEquals("ccc.bbb.aaa.ddd.eee", BlancoNameUtil
                .uri2JavaPackage("https://aaa.bbb.ccc/ddd/eee"));
        assertEquals("ccc.bbb.aaa.ddd.eee", BlancoNameUtil
                .uri2JavaPackage("https://aaa.bbb.ccc:8080/ddd/eee"));
        assertEquals("ccc.bbb.aaa.ddd.eee", BlancoNameUtil
                .uri2JavaPackage("ftp://aaa.bbb.ccc:43/ddd/eee"));
        assertEquals("aaa", BlancoNameUtil.uri2JavaPackage("http://aaa"));
        assertEquals("aaa.bbb", BlancoNameUtil
                .uri2JavaPackage("http://aaa/bbb"));
        assertEquals("aaa.bbb.ccc", BlancoNameUtil
                .uri2JavaPackage("http://aaa/bbb/ccc"));
        assertEquals("org.aaa", BlancoNameUtil
                .uri2JavaPackage("http://aaa.org"));
        assertEquals("org.aaa", BlancoNameUtil
                .uri2JavaPackage("http://aaa.org/"));
        try {
            BlancoNameUtil.uri2JavaPackage("abc.def");
            fail("��O���������ׂ��Ƃ���ŁA��O���������܂���ł����B");
        } catch (IllegalArgumentException e) {
        }
    }

    private void assertEqualsStringArray(String[] wish, String[] target)
            throws Exception {
        assertEquals(wish.length, target.length);
        for (int index = 0; index < wish.length; index++) {
            assertEquals(wish[index], target[index]);
        }
    }
}
