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
package blanco.commons.calc.parser;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import junit.framework.TestCase;

import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import blanco.commons.calc.parser.block.BlancoCalcParserPropertyBlock;
import blanco.commons.calc.parser.block.BlancoCalcParserPropertyKey;
import blanco.commons.calc.parser.block.BlancoCalcParserTableBlock;
import blanco.commons.calc.parser.block.BlancoCalcParserTableColumn;
import blanco.commons.calc.parser.block.BlancoCalcParserValueMapping;
import blanco.commons.parser.SystemOutContentHandler;

/**
 * @author iga
 */
public class BlancoCalcParserTest extends TestCase {

    /*
     * void parse �̃e�X�g���̃N���X(String)
     */
    @SuppressWarnings("deprecation")
    public void testParseString() {
        OutputStream outStream = null;

        BlancoCalcParserPropertyBlock propertyBlock = new BlancoCalcParserPropertyBlock(
                "attribute");
        propertyBlock.setStartString(new String[] { "�Ɩ�" });
        propertyBlock
                .setValueMapping(new BlancoCalcParserValueMapping[] {
                        new BlancoCalcParserValueMapping(new String[] { "��",
                                "����" }, "true"),
                        new BlancoCalcParserValueMapping(new String[] { "�Ȃ�" },
                                "false"),
                        new BlancoCalcParserValueMapping(
                                new String[] { "�����^" }, "iterator"),
                        new BlancoCalcParserValueMapping(
                                new String[] { "���s�^" }, "invoker"),
                        new BlancoCalcParserValueMapping(
                                new String[] { "�K���P������" }, "true"),
                        new BlancoCalcParserValueMapping(
                                new String[] { "������(0���܂�)" }, "false") });

        BlancoCalcParserPropertyKey propertyTitle = new BlancoCalcParserPropertyKey(
                "gamen-id", new String[] { "���ID" });
        propertyTitle.setSearchRangeX(4);
        propertyBlock.add(propertyTitle);

        propertyTitle = new BlancoCalcParserPropertyKey("name",
                new String[] { "�N�G����" });
        propertyTitle.setSearchRangeX(4);
        propertyBlock.add(propertyTitle);

        propertyTitle = new BlancoCalcParserPropertyKey("query-type",
                new String[] { "SQL�^�C�v" });
        propertyTitle.setSearchRangeX(8);
        propertyBlock.add(propertyTitle);

        propertyTitle = new BlancoCalcParserPropertyKey("single",
                new String[] { "���҂��鏈������" });
        propertyTitle.setSearchRangeX(8);
        propertyBlock.add(propertyTitle);

        propertyTitle = new BlancoCalcParserPropertyKey("scroll",
                new String[] { "�X�N���[������" });
        propertyTitle.setSearchRangeX(8);
        propertyBlock.add(propertyTitle);

        propertyTitle = new BlancoCalcParserPropertyKey("updatable",
                new String[] { "�X�V�\����" });
        propertyTitle.setSearchRangeX(8);
        propertyBlock.add(propertyTitle);

        BlancoCalcParser parser = new BlancoCalcParser();
        parser.add(propertyBlock);

        // �ʂ̃u���b�N
        BlancoCalcParserTableBlock tableBlock = new BlancoCalcParserTableBlock(
                "parameters");
        tableBlock.setStartString(new String[] { "SQL���̓p�����[�^" });
        tableBlock.setRowName("parameter");
        tableBlock.setSearchRangeForTitleY(2);
        BlancoCalcParserTableColumn tableTitle = new BlancoCalcParserTableColumn(
                "name", new String[] { "�p�����[�^ID" });
        tableBlock.add(tableTitle);

        tableTitle = new BlancoCalcParserTableColumn("type",
                new String[] { "�^�C�v" });
        tableBlock.add(tableTitle);

        tableTitle = new BlancoCalcParserTableColumn("�p�����[�^��",
                new String[] { "�p�����[�^��" });
        tableBlock.add(tableTitle);

        parser.add(tableBlock);

        // �ʂ̃u���b�N
        tableBlock = new BlancoCalcParserTableBlock("query");
        tableBlock.setStartString(new String[] { "SQL��" });
        tableBlock.setEndString(new String[] { "SQL�G���A�I��" });
        tableBlock.setSearchRangeForTitleY(1);
        tableBlock.setSearchRangeY(100);
        tableTitle = new BlancoCalcParserTableColumn("query-line",
                new String[] { "SQL��" });
        tableBlock.add(tableTitle);
        parser.add(tableBlock);

        // ��̓I�ȃn���h���� ���̃e�X�g����͏������܂��B
        parser.chainContentHandlerStream(new BlancoDbExcelContentHandler(
                new SystemOutContentHandler()));

        try {
            // ���[�g�m�[�h�����w��B
            parser.setProperty(BlancoCalcParser.URI_PROPERTY_NAME_WORKBOOK,
                    "blanco-db");
            outStream = new BufferedOutputStream(new FileOutputStream(
                    "./meta/blancoCalcParserTestData.xml"));
            BlancoCalcParser.getTransformer().transform(
                    new SAXSource(parser, new InputSource(
                            "./meta/blancoCalcParserTestData.xls")),
                    new StreamResult(outStream));
            outStream.flush();
            outStream.close();
            outStream = null;
        } catch (TransformerException ex) {
            System.out.println("XML�h�L�������g�ۑ����ɕϊ���O���������܂���.:" + ex.toString());
            ex.printStackTrace();
            return;
        } catch (IOException ex3) {
            System.out.println("XML�h�L�������g�ۑ����ɓ��o�͗�O���������܂���.:" + ex3.toString());
            ex3.printStackTrace();
            return;
        } catch (SAXNotRecognizedException e) {
            // TODO �����������ꂽ catch �u���b�N
            e.printStackTrace();
        } catch (SAXNotSupportedException e) {
            // TODO �����������ꂽ catch �u���b�N
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
