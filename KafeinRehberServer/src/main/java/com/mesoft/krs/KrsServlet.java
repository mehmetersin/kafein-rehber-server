package com.mesoft.krs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KrsServlet extends HttpServlet {

	final static Logger logger = LoggerFactory.getLogger(KrsServlet.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String cmd = req.getParameter("cmd");
		PrintWriter out = resp.getWriter();

		// String remoteId = req.getRemoteAddr();
		// String remoteHost = req.getRemoteHost();

		logger.debug("Request Parameters . Cmd {} ", cmd);

		try {

			String response = null;

			List<Contact> cList = ReadExcelExample.getPhoneList();

			for (Iterator iterator = cList.iterator(); iterator.hasNext();) {
				Contact contact = (Contact) iterator.next();

				if (response == null) {
					response = contact.toString();
				} else {
					response = response + "|" + contact.toString();
				}

			}

			StringBuffer xmlStr = new StringBuffer();
			xmlStr.append(response);

			logger.debug("Response Message {}", xmlStr.toString());
			out.print(xmlStr.toString());
		} catch (Exception e) {
			logger.error("Error while processing", e);
			out.print("ERROR");
		}

	}
}