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
package blanco.commons.calc;

/**
 * �\�v�Z�Ƃ��ăJ�����ԍ����烉�x����������擾���܂��B
 * 
 * @author IGA Tosiki
 */
public final class BlancoCalcUtil {
    private BlancoCalcUtil() {
        // �R���X�g���N�^�͖���������Ă��܂��B
    }

    /**
     * �^����ꂽ�J�����ԍ����J�����̃��x��������ɕϊ����܂��B
     * 
     * @param column
     *            �J������\�����l�B�P�I���W���Ŏw�肵�܂��B
     * @return ��F26=Z, 27=AA
     */
    public static final String columnToLabel(int column) {
        String output = "";
        for (;;) {
            if (column > 26) {
                output = columnToLabel((column - 1) % 26 + 1) + output;
                column = (column - 1) / 26;
            } else {
                return Character.toString((char) ('A' + column - 1)) + output;
            }
        }
    }
}
