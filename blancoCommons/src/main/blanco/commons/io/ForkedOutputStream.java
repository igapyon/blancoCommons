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

import java.io.IOException;
import java.io.OutputStream;

/**
 * �ЂƂ̏o�̓X�g���[�����A�����̏o�̓X�g���[���ɕ����邽�߂̃N���X�ł��B
 * 
 * @author IGA Tosiki
 */
public class ForkedOutputStream extends OutputStream {
    private OutputStream[] outStreams = null;

    /**
     * �ЂƂ̃X�g���[���ւ̏o�͂��Q�̃X�g���[���ւ̏o�͂ɕ����܂��B
     * 
     * @param outStream1
     * @param outStream2
     */
    public ForkedOutputStream(final OutputStream outStream1,
            final OutputStream outStream2) {
        this(new OutputStream[] { outStream1, outStream2 });
    }

    /**
     * �ЂƂ̃X�g���[���ւ̏o�͂��R�̃X�g���[���ւ̏o�͂ɕ����܂��B
     * 
     * @param outStream1
     * @param outStream2
     * @param outStream3
     */
    public ForkedOutputStream(final OutputStream outStream1,
            final OutputStream outStream2, final OutputStream outStream3) {
        this(new OutputStream[] { outStream1, outStream2, outStream3 });
    }

    /**
     * �ЂƂ̃X�g���[���ւ̏o�͂�C�ӂ̐��̃X�g���[���ւ̏o�͂ɕ����܂��B
     */
    public ForkedOutputStream(final OutputStream[] arg) {
        outStreams = new OutputStream[arg.length];
        for (int index = 0; index < arg.length; index++) {
            outStreams[index] = arg[index];
        }
    }

    public void write(final int arg) throws IOException {
        for (int index = 0; index < outStreams.length; index++) {
            outStreams[index].write(arg);
        }
    }

    public void write(final byte arg[], final int offset, final int length)
            throws IOException {
        for (int index = 0; index < outStreams.length; index++) {
            outStreams[index].write(arg, offset, length);
        }
    }

    public void write(final byte arg[]) throws IOException {
        write(arg, 0, arg.length);
    }

    /**
     * �X�g���[����2�Ƃ��t���b�V�����܂��B
     */
    public void flush() throws IOException {
        for (int index = 0; index < outStreams.length; index++) {
            outStreams[index].flush();
        }
    }

    /**
     * �X�g���[����2�Ƃ��N���[�Y���܂��B
     */
    public void close() throws IOException {
        for (int index = 0; index < outStreams.length; index++) {
            outStreams[index].close();
        }
    }
}
