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
package blanco.commons.calc.parser.block;

/**
 * �\���p�̒l�Ǝ��ۂ̒l�Ƃ��}�b�s���O���܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoCalcParserValueMapping {
    private String[] source = null;

    private String target = null;

    public BlancoCalcParserValueMapping(final String[] source,
            final String target) {
        this.source = source;
        this.target = target;
    }

    public void setSource(final String[] args) {
        source = args;
    }

    public String[] getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    /**
     * ������}�b�s���O���s���܂��B�}�b�s���O�����s�����ꍇ�ɂ�null��߂��܂��B
     * 
     * @param value
     *            ���͒l�B
     * @return �}�b�s���O�Ƀq�b�g������ϊ���̒l��߂��܂��B�}�b�s���O�Ƀq�b�g���Ȃ������ꍇ�ɂ͓��͒l�����̂܂ܖ߂��܂��B
     */
    private String mapping(final String value) {
        if (source == null) {
            return null;
        }

        final int sourceLength = source.length;
        for (int index = 0; index < sourceLength; index++) {
            if (source[index].equals(value)) {
                return target;
            }
        }
        return null;
    }

    /**
     * �l�̃}�b�s���O���s���܂��B�}�b�s���O�ł��Ȃ������ꍇ�ɂ� ���͒l�����̂܂ܖ߂��܂��B
     * 
     * @param value
     *            �}�b�s���O�̃}�b�`���O���s���������͒l�B
     * @param mappings
     *            �}�b�s���O�\�B
     * @return �}�b�s���O��̒l�B�}�b�s���O���s���Ȃ������ꍇ�ɂ͓��͒l���̂��́B
     */
    public static final String mapping(final String value,
            final BlancoCalcParserValueMapping[] mappings) {
        if (mappings == null) {
            return value;
        }

        final int mappingCount = mappings.length;
        for (int index = 0; index < mappingCount; index++) {
            String result = mappings[index].mapping(value);
            if (result != null) {
                return result;
            }
        }
        return value;
    }
}
