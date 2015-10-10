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

/**
 * blanco Framework�ɂ�����A�o�C�g�Ɋւ��郆�[�e�B���e�B���܂܂�܂��B
 * 
 * �����Ƃ��ĂقƂ�ǂ̃��\�b�h��static���\�b�h�Ƃ��Ē񋟂���܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoByteUtil {
    /**
     * �o�C�g�z��̓��e���r���܂��B
     * 
     * @param arg0
     *            ���Ӓl�B
     * @param arg1
     *            �E�Ӓl�B
     * @return ��v�����ꍇ��0�B����ȊO�̏ꍇ�ɂ͍��Ӓl-�E�Ӓl�B
     */
    public static final int compare(final byte[] arg0, final byte[] arg1) {
        if (arg0 == null) {
            throw new IllegalArgumentException(
                    "BlancoStreamUtil.compare�̓��̓p�����[�^��1�Ԗڂ�null���^�����܂����B");
        }
        if (arg1 == null) {
            throw new IllegalArgumentException(
                    "BlancoStreamUtil.compare�̓��̓p�����[�^��2�Ԗڂ�null���^�����܂����B");
        }

        for (int index = 0;; index++) {
            // �ŏ��ɒ����`�F�b�N�������Ȃ��܂��B
            if (index == arg0.length) {
                // �����𒴂��Ă��܂��܂����B
                if (index == arg1.length) {
                    // �Ō�̍Ō�܂ň�v���Ă��܂����B
                    return 0;
                } else {
                    // �E�Ӓl���傫���ł��B
                    return -1;
                }
            } else if (index == arg1.length) {
                // �����𒴂��Ă��܂��܂����B
                // ���Ӓl���傫���ł��B
                return 1;
            }
            if (arg0[index] == arg1[index]) {
                continue;
            } else {
                // ��v���܂���ł����B
                return arg0[index] - arg1[index];
            }
        }
    }
}
