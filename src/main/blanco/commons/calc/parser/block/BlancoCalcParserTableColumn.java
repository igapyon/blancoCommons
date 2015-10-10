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
 * �e�[�u�����\�����܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoCalcParserTableColumn {

    private String _columnName = "name";

    private String[] _columnString = null;

    private int columnPosition = -1;

    /**
     * �e�[�u���̃J�����I�u�W�F�N�g�̃R���X�g���N�^�B
     * 
     * @param name
     *            �J�������B
     */
    public BlancoCalcParserTableColumn(final String name) {
        this._columnName = name;
    }

    /**
     * �e�[�u���̗��ރI�u�W�F�N�g�̃R���X�g���N�^�B
     * 
     * @param name
     *            �J�������B
     * @param columnString
     *            �J����������̔z��B
     */
    public BlancoCalcParserTableColumn(final String name,
            final String[] columnString) {
        this._columnName = name;
        this._columnString = columnString;
    }

    /**
     * ��̖��O���擾���܂��B
     * 
     * @return ��̖��O�B
     */
    public String getName() {
        return _columnName;
    }

    /**
     * ��̖��O��ݒ肵�܂��B
     * 
     * @param arg
     *            ��̖��O�B
     */
    public void setName(final String arg) {
        _columnName = arg;
    }

    /**
     * �J������������L�����܂��B
     * 
     * @param arg
     *            �J����������̔z��B
     */
    public void setColumnString(final String[] arg) {
        _columnString = arg;
    }

    /**
     * �J�n������Ƀq�b�g���邩�ǂ����������܂��B
     * 
     * @param arg
     *            �J�n������B
     * @return �q�b�g�������ǂ����B
     */
    public boolean isStartString(String arg) {
        final int columnStringLength = _columnString.length;
        for (int index = 0; index < columnStringLength; index++) {
            if (_columnString[index].equals(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ��̃J�����ԍ����擾���܂��B
     * 
     * @return ��̃J�����ԍ��B
     */
    public int getColumnPosition() {
        return columnPosition;
    }

    /**
     * ��̃J�����ԍ����L�����܂��B
     * 
     * @param arg
     *            ��̃J�����ԍ��B
     */
    public void setColumnPosition(int arg) {
        columnPosition = arg;
    }
}
