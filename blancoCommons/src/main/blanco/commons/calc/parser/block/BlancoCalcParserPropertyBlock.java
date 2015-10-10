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
 * �L�[�}�b�v�̃u���b�N���������܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoCalcParserPropertyBlock extends
        AbstractBlancoCalcParserBlock {

    private List<BlancoCalcParserPropertyKey> list = new ArrayList<BlancoCalcParserPropertyKey>();

    /**
     * �v���p�e�B�u���b�N�̃R���X�g���N�^�B
     * 
     * @param name
     *            �u���b�N���B
     */
    public BlancoCalcParserPropertyBlock(String name) {
        setName(name);
    }

    /**
     * �v���p�e�B�u���b�N�Ƀv���p�e�B�L�[��ǉ����܂��B
     * 
     * @param item
     *            �v���p�e�B�L�[�I�u�W�F�N�g�B
     */
    public void add(BlancoCalcParserPropertyKey item) {
        list.add(item);
    }

    /**
     * �J�n����������������ɗ�A�C�e�����������܂��B
     * 
     * @param startString
     *            �J�n������B
     * @return �������ꂽ�v���p�e�B�L�[�I�u�W�F�N�g�B
     */
    public BlancoCalcParserPropertyKey findByStartString(String startString) {
        final int listSize = list.size();
        for (int index = 0; index < listSize; index++) {
            final BlancoCalcParserPropertyKey item = (BlancoCalcParserPropertyKey) list
                    .get(index);
            if (item.isStartString(startString)) {
                return item;
            }
        }
        return null;
    }
}
