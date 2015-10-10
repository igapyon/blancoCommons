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

import java.util.ArrayList;
import java.util.List;

/**
 * �J��Ԃ��^�̃u���b�N���������܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoCalcParserTableBlock extends AbstractBlancoCalcParserBlock {
    private List<BlancoCalcParserTableColumn> list = new ArrayList<BlancoCalcParserTableColumn>();

    private String blockRowName = "";

    /**
     * �^�C�g�����T�[�`����ۂɁA�^�C�g���̍s���i�ʏ�͂P�s�j �̐���񋟂��܂��B
     */
    private int waitForIteratorTitleSearchY = 1;

    /**
     * �J��Ԃ��^�̃u���b�N�I�u�W�F�N�g�̃R���X�g���N�^�ł��B
     * 
     * @param name
     *            �u���b�N���B
     */
    public BlancoCalcParserTableBlock(String name) {
        setName(name);
    }

    /**
     * �J��Ԃ��^�̃u���b�N�I�u�W�F�N�g�ɃJ������ǉ����܂��B
     * 
     * @param item
     *            �u���b�N�ւƒǉ��������J�����B
     */
    public void add(BlancoCalcParserTableColumn item) {
        list.add(item);
    }

    /**
     * �^�C�g���s�����������ɁA��A�C�e�����������܂��B
     * 
     * @param titleString
     *            �^�C�g��������B
     * @return �������ꂽ��A�C�e���B
     */
    public BlancoCalcParserTableColumn findByTitleString(String titleString) {
        final int listSize = list.size();
        for (int index = 0; index < listSize; index++) {
            final BlancoCalcParserTableColumn item = (BlancoCalcParserTableColumn) list
                    .get(index);
            if (item.isStartString(titleString)) {
                return item;
            }
        }
        return null;
    }

    /**
     * ��ԍ������������ɁA��A�C�e�����������܂��B
     * 
     * @param pos
     *            ��̔ԍ��B
     * @return �������ꂽ��A�C�e���B
     */
    public BlancoCalcParserTableColumn findByColumnPosition(int pos) {
        final int listSize = list.size();
        for (int index = 0; index < listSize; index++) {
            final BlancoCalcParserTableColumn item = (BlancoCalcParserTableColumn) list
                    .get(index);
            if (item.getColumnPosition() == pos) {
                return item;
            }
        }
        return null;
    }

    /**
     * �x�����̌����͈͂��擾���܂��B
     * 
     * @return �x�����̌����͈́B
     */
    public int getSearchRangeForTitleY() {
        return waitForIteratorTitleSearchY;
    }

    /**
     * �^�C�g�������񂩂�̂w�����̌����͈͂��擾���܂��B
     * 
     * @param arg
     *            �w�����̌����͈́B
     */
    public void setSearchRangeForTitleY(int arg) {
        waitForIteratorTitleSearchY = arg;
    }

    /**
     * ��̖��O��ݒ肵�܂��B
     * 
     * @param arg
     *            ��̖��O�B
     */
    public void setRowName(String arg) {
        blockRowName = arg;
    }

    /**
     * ��̖��O���擾���܂��B
     * 
     * @return ��̖��O�B
     */
    public String getRowName() {
        return blockRowName;
    }
}
