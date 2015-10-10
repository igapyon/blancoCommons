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
 * ���ۓI�ȃu���b�N��\�����܂��B
 * 
 * @author IGA Tosiki
 */
public abstract class AbstractBlancoCalcParserBlock {

    /**
     * Y�����̉䖝����� (�����^�C�g���s�̃J�E���g����)
     */
    private int waitForValueY = 1;

    /**
     * �u���b�N�̊J�n������Q
     */
    private String[] startString = null;

    /**
     * �u���b�N�̏I��������Q
     */
    private String[] endString = null;

    /**
     * �u���b�N�ɗ^����ꂽ���O(ID)
     */
    private String blockName = "name";

    /**
     * �l�}�b�s���O���
     */
    private BlancoCalcParserValueMapping[] valueMapping = null;

    /**
     * �u���b�N�̖��O���擾���܂��B
     * 
     * @return �u���b�N���B
     */
    public String getName() {
        return blockName;
    }

    /**
     * �u���b�N�̖��O��ݒ肵�܂��B
     * 
     * @param arg
     */
    public void setName(String arg) {
        blockName = arg;
    }

    /**
     * �J�n������Q���Z�b�g���܂�
     * 
     * @param arg
     *            �J�n������̔z��B
     */
    public void setStartString(String[] arg) {
        startString = arg;
    }

    /**
     * �J�n������Ƀq�b�g���邩�ǂ����������܂��B
     * 
     * @param arg
     *            �`�F�b�N���s������������B
     * @return �J�n������Ƀq�b�g�������ǂ����B
     */
    public boolean isStartString(String arg) {
        if (startString == null) {
            return false;
        }

        final int startStringLength = startString.length;
        for (int index = 0; index < startStringLength; index++) {
            if (startString[index].equals(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * �I��������Q���Z�b�g���܂�
     * 
     * @deprecated �I��������̃`�F�b�N�͐�������܂���B
     * @param arg
     *            �I��������̔z��B
     */
    public void setEndString(String[] arg) {
        endString = arg;
    }

    /**
     * �I��������Ƀq�b�g���邩�ǂ����������܂��B
     * 
     * @deprecated �I��������̃`�F�b�N�͐�������܂���B
     * @param arg
     *            �`�F�b�N���s������������B
     * @return �q�b�g�������ǂ����B
     */
    public boolean isEndString(String arg) {
        if (endString == null) {
            return false;
        }

        final int endStringLength = endString.length;
        for (int index = 0; index < endStringLength; index++) {
            if (endString[index].equals(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * �x�����̌����͈͂��w�肵�܂��B
     * 
     * @param arg
     *            �x�����̌����͈́B
     */
    public void setSearchRangeY(int arg) {
        waitForValueY = arg;
    }

    /**
     * �x�����̌����͈͂��擾���܂��B
     * 
     * @return �x�����̌����͈́B
     */
    public int getSearchRangeY() {
        return waitForValueY;
    }

    /**
     * �l�̓ǂݑւ��}�b�s���O���w�肵�܂��B
     * 
     * @param mapping
     *            �l�̓ǂݑւ��}�b�s���O�B
     */
    public void setValueMapping(BlancoCalcParserValueMapping[] mapping) {
        valueMapping = mapping;
    }

    /**
     * �l�̓ǂݑւ��}�b�s���O���擾���܂��B
     * 
     * @return �l�̓ǂݑւ��}�b�s���O�B
     */
    public BlancoCalcParserValueMapping[] getValueMapping() {
        return valueMapping;
    }
}
