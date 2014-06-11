package org.liufeng.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.util.MessageUtil;
import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;

import com.sun.corba.se.spi.legacy.connection.LegacyServerSocketEndPointInfo;

/**
 * ���ķ�����
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "δ֪����Ϣ���ͣ�";
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			List<Article> articleList = new ArrayList<Article>();

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("������ʢ�Ļ���ý��˾");
					article.setDescription("�人�н��������������ƽ�ɫ��԰������ҵ��321��");
					article.setPicUrl("https://weixinshgj.duapp.com/image/mp.jpg");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200332988&idx=1&sn=c037f49bd41b4ed5622e0ac2e0e6a14a#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("3".equals(content)) {

				} else if ("4".equals(content)) {

				} else if ("5".equals(content)) {

				} else if ("6".equals(content)) {

				} else if ("7".equals(content)) {

				} else {
					textMessage.setContent(getDefaultAnswer());
					respXml = MessageUtil.messageToXml(textMessage);
				}
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					Article article1 = new Article();
					article1.setTitle("������ʢ�Ļ���ý��˾");
					article1.setDescription("�人�н��������������ƽ�ɫ��԰������ҵ��321��");
					article1.setPicUrl("https://weixinshgj.duapp.com/image/logo2.jpg");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200332988&idx=1&sn=c037f49bd41b4ed5622e0ac2e0e6a14a#rd");
					Article article2 = new Article();
					article2.setTitle("��л����ע������ʢ�Ļ���ý�������ȡ������Ѷ");
					article2.setDescription("");
					article2.setPicUrl("https://weixinshgj.duapp.com/image/logo_s.jpg");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200332988&idx=1&sn=c037f49bd41b4ed5622e0ac2e0e6a14a#rd");
					articleList.add(article1);
					articleList.add(article2);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				}
				// ȡ����ע
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}
				// ɨ���������ά��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO ����ɨ���������ά���¼�
				}
				// �ϱ�����λ��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO �����ϱ�����λ���¼�
				}
				// �Զ���˵�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO ����˵�����¼�
				}
			}
			// �����ı���Ϣ������
			// textMessage.setContent(respContent);
			// ���ı���Ϣ����ת����xml
			// respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	private static String getDefaultAnswer() {
		String[] answer = { "Ҫ�������ĵ��ģ�", "�����㵽����˵ʲô�أ�", "û��������˵�ģ��ܷ񻻸�˵��",
				"��Ȼ�����������˼������ȴ������ȥ����", "������һͷ��ˮ�����µ�ֻ������Ԩ������Ĥ��~",
				"������Сѧ������������ʦ�̵ģ���������е����Ѱ�", "������仯̫�죬�����Ҳ����вţ�Ϊ����˵���Ҳ����ף�", };
		return answer[getRandomNumber(answer.length)];
	}

	private static int getRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}
}
