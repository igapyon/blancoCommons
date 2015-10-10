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

import java.math.BigDecimal;

/**
 * blanco Framework�ɂ�����ABigDecimal�Ɋւ��郆�[�e�B���e�B���܂܂�܂��B
 * 
 * �����Ƃ��ĂقƂ�ǂ̃��\�b�h��static���\�b�h�Ƃ��Ē񋟂���܂��B
 * 
 * @author IGA Tosiki
 */
public class BlancoBigDecimalUtil {
    /**
     * int��BigDecimal�ɕϊ����܂��B
     * 
     * BigDecimal�̃R���X�g���N�^�Ő��l����͂�����̂�JDK 1.5�ȍ~�ɓ�������Ă��܂��B<br>
     * ���̃��\�b�h�́A1.4�ȑO�œ��삳���邽�߂ɁABigDecimal��JDK
     * 1.5�ȍ~�œ������ꂽ�R���X�g���N�^������ė��p���Ă��܂�Ȃ����߂̃��\�b�h�ł��B
     * 
     * @param valueSource
     *            �ϊ����Ƃ̒l�B
     * @return BigDecimal�ɕϊ���̒l�B
     */
    public static final BigDecimal toBigDecimal(final int valueSource) {
        return new BigDecimal(String.valueOf(valueSource));
    }
}
