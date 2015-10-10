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
 * �v���p�e�B�L�[��\�����܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoCalcParserPropertyKey {

    private String keyName = "name";

    private String[] startString = null;

    /**
     * �f�t�H���g��1��̂ݓǂݍ��݂܂��B
     */
    private int waitForValueX = 1;

    /**
     * �v���p�e�B�L�[�I�u�W�F�N�g�̃R���X�g���N�^�B
     * 
     * @param name
     *            �v���p�e�B�L�[�̖��O�B
     */
    public BlancoCalcParserPropertyKey(final String name) {
        this.keyName = name;
    }

    /**
     * �v���p�e�B�L�[�I�u�W�F�N�g�̃R���X�g���N�^�B
     * 
     * @param name
     *            �v���p�e�B�L�[�̖��O�B
     * @param startString
     *            �J�n������̔z��B
     */
    public BlancoCalcParserPropertyKey(final String name,
            final String[] startString) {
        this.keyName = name;
        this.startString = startString;
    }

    /**
     * �L�[�̖��O���擾���܂��B
     * 
     * @return �L�[�̖��O�B
     */
    public String getName() {
        return keyName;
    }

    /**
     * �L�[�̖��O��ݒ肵�܂��B
     * 
     * @param arg
     *            �L�[�̖��O�B
     */
    public void setName(String arg) {
        keyName = arg;
    }

    /**
     * �J�n������Q���Z�b�g���܂��B
     * 
     * @param arg
     *            �J�n������̔z��B
     */
    public void setKeyString(String[] arg) {
        startString = arg;
    }

    /**
     * �J�n������Ƀq�b�g���邩�ǂ����������܂��B
     * 
     * @param arg
     *            �`�F�b�N���s������������B
     * @return �q�b�g�������ǂ����B
     */
    public boolean isStartString(String arg) {
        final int startStringLength = startString.length;
        for (int index = 0; index < startStringLength; index++) {
            if (startString[index].equals(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * �w�����̌����͈͂�ݒ肵�܂��B
     * 
     * @param arg
     *            �w�����̌����͈́B
     */
    public void setSearchRangeX(int arg) {
        waitForValueX = arg;
    }

    /**
     * �w�����̌����͈͂��擾���܂��B
     * 
     * @return �w�����̌����͈́B
     */
    public int getSearchRangeX() {
        return waitForValueX;
    }
}
