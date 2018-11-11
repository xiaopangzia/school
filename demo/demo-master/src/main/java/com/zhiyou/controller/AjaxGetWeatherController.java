package com.zhiyou.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zhiyou.model.City;
import com.zhiyou.model.Hourly;
import com.zhiyou.model.RWeather;
import com.zhiyou.model.Weather;
import com.zhiyou.service.CityService;
import com.zhiyou.service.WeatherService;

/**
 * 异步请求返回数据 同步请求返回视图
 * 
 * @author jack
 *
 */
@WebServlet("/getWeather")
public class AjaxGetWeatherController extends HttpServlet {

	private static final long serialVersionUID = 8110152927594343930L;
	private CityService cityService = new CityService();
	private WeatherService weatherService = new WeatherService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.从CityService获取城市数据
		// 2.在对应的jsp页面展示数据
		try {
			String cityid = req.getParameter("cityid");
			System.out.println("选择到的城市id：" + cityid);
			if (cityid != null && !"".equals(cityid)) {
				//根据cityid获取城市信息
				City city = cityService.getCityById(cityid);
				// 通过city获取对应城市的天气信息
				RWeather r = weatherService.getWeatherInfo(city.getCity(),city.getCitycode(),city.getCityid());
				Weather w = r.getResult();
				// 每小时的天气情况
				List<Hourly> hourlys = w.getHourly();
				// 存放未来24小时的时候
				List<String> times = new ArrayList<String>();
				// 存放未来24小时的温度
				List<String> temps = new ArrayList<String>();
				for (Hourly h : hourlys) {
					// 获取时间
					String time = h.getTime();
					// 获取该时间对应的温度
					String temp = h.getTemp();
					times.add(time);
					temps.add(temp);
				}
				JSONObject obj = new JSONObject();
				obj.put("temps", temps);
				obj.put("times", times);
				//System.out.println(obj);
				//不再返回视图而是返回数据
				resp.getWriter().write(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
