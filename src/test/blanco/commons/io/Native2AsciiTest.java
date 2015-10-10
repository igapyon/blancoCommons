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

import junit.framework.TestCase;

/**
 * BlancoSqlFormatter: SQL���`�c�[��. SQL�������߂�ꂽ���[���ɏ]�����`���܂��B <br>
 * SQL���Ƃ��Đ��������Ƃ��O������ł��B
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html <br>
 * ������SQL���`�R�[�f�B���O���[���ɏ]���A�P�̎��������{���܂��B
 * 
 * @author iga
 */
public class Native2AsciiTest extends TestCase {
    /**
     * �L�[������
     * 
     * @throws Exception
     */
    public void testNative2Key() throws Exception {
        // �L�[�Ȃ�ł͂̑���_
        assertEquals("\\ ABC", Native2AsciiWriter.encodeNative2AsciiKey(" ABC"));
        assertEquals("A\\=BC", Native2AsciiWriter.encodeNative2AsciiKey("A=BC"));
        assertEquals("A\\:BC", Native2AsciiWriter.encodeNative2AsciiKey("A:BC"));

        native2AsciiCommonCheck(true);
    }

    /**
     * �l������
     */
    public void testNative2AsciiValue() throws Exception {
        assertEquals("\\u3048\\u304A\\u304B\\u304D", Native2AsciiWriter
                .encodeNative2AsciiValue("��������"));
        assertEquals("\\ ABC", Native2AsciiWriter
                .encodeNative2AsciiValue(" ABC"));
        assertEquals("A\\=BC", Native2AsciiWriter
                .encodeNative2AsciiValue("A=BC"));
        assertEquals("A\\:BC", Native2AsciiWriter
                .encodeNative2AsciiValue("A:BC"));

        native2AsciiCommonCheck(false);
    }

    /**
     * �l������
     */
    private void native2AsciiCommonCheck(final boolean isKey) throws Exception {
        // ��ʓI�Ȏ����ϓ_
        assertEquals("A\\tBC", Native2AsciiWriter
                .encodeNative2AsciiKey("A\tBC"));
        assertEquals("A\\nBC", Native2AsciiWriter
                .encodeNative2AsciiKey("A\nBC"));
        assertEquals("A\\rBC", Native2AsciiWriter
                .encodeNative2AsciiKey("A\rBC"));
        assertEquals("A\"BC", Native2AsciiWriter.encodeNative2AsciiKey("A\"BC"));

        assertEquals("A\\tBC", Native2AsciiWriter
                .encodeNative2AsciiValue("A\tBC"));
        assertEquals("A\\nBC", Native2AsciiWriter
                .encodeNative2AsciiValue("A\nBC"));
        assertEquals("A\\rBC", Native2AsciiWriter
                .encodeNative2AsciiValue("A\rBC"));
        assertEquals("A\"BC", Native2AsciiWriter
                .encodeNative2AsciiValue("A\"BC"));
    }

    /**
     * �R�����g�s�̃G���R�[�h����
     * 
     * @throws Exception
     */
    public void testComment() throws Exception {
        assertEquals("\\u3048\\u304A\\u304B", Native2AsciiWriter
                .encodeNative2AsciiComment("������"));
        // �R�����g�s�� : �̃G�X�P�[�v�͂���܂���B
        assertEquals("A:B:C", Native2AsciiWriter
                .encodeNative2AsciiComment("A:B:C"));
    }
}
