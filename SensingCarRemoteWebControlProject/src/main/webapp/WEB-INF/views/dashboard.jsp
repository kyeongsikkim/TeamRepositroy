<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name=viewport
			content="width=device-width initial-scale=1 user-scalable=no">
		<title>Home</title>
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
		<link href='https://fonts.googleapis.com/css?family=Roboto:400' rel='stylesheet' type='text/css'>
		<link href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/custom_css/inputrange.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/custom_css/switch.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/custom_css/lcd.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/custom_css/bulb.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/custom_css/button.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/custom_css/button2.css" rel="stylesheet" type="text/css" />
		<link href="<%=application.getContextPath()%>/resources/custom_css/rgb.css" rel="stylesheet" type="text/css" />
		
		<script src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/highcharts-more.js"></script>
		<script src="https://code.highcharts.com/modules/solid-gauge.js"></script>
		
		<script src="https://use.fontawesome.com/b36942a4d5.js"></script>
		
		<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Bubblegum+Sans" />
		<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Hammersmith+One" />
		<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Droid+Sans" />
		<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Indie+Flower" />
		<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Gloria+Hallelujah" />
		<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Contrail+One" />
			
		<script src="<%=application.getContextPath()%>/resources/js/camera.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/lcd.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/activebuzzer.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/laseremitter.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/backtiregauge.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/backtire.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/fronttire.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicsensor.js"></script>		
		<script src="<%=application.getContextPath()%>/resources/js/rgb.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/rgbimpl.js"></script>
			
		<script src="<%=application.getContextPath()%>/resources/js/showsensorchart.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/thermistorsensorvalue.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/photoresistorsensorvalue.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/trackingsensorvalue.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/ultrasonicsensorvalue.js"></script>
		<script src="<%=application.getContextPath()%>/resources/js/gassensorvalue.js"></script>
		<script type="text/javascript">
			$(function(){
				if("${buzzerStatus}" == "on") {
					$("#buzzeronoff").removeAttr("checked", "checked");
				};
				if("${laseremitterStatus}" == "on") {
					$("#laseronoff").removeAttr("checked");
				}
				setSpeedGaugeDefault("${speed}");
			})
		</script>
		<style>
			.bannerhr {
				height: 1px;
				width: 70%;
				color: #ffffff;
				background-color: #ffffff;
				border: none;
				margin-top: 12px;
				margin-bottom: 12px;
			}
			hr {
				height: 2px;
				color: #cccccc;
				background-color: #cccccc;
				border: none;
			}
			.topnav {
			  overflow: hidden;
			  background-color: rgba(0, 0, 0, 0.7);
			  width: 100%;
			  position: fixed;
			  z-index: 999;
			}
			
			.topnav a {
			  float: left;
			  display: block;
			  color: #f2f2f2;
			  text-align: center;
			  padding: 14px 16px;
			  text-decoration: none;
			  font-size: 17px;
			}
			
			.topnav a:hover {
			  background-color: #ddd;
			  color: black;
			}
			
			.topnav a.active {
			    background-color: #4CAF50;
			    color: white;
			}
			.highcharts-yaxis-grid .highcharts-grid-line {
				display: none;
			}
		</style>
	</head>
	<body>
		<div class="topnav">
		  <a href="#top">TOP</a>
		  <a href="#realTimeData">RealTime Data</a>
		  <a href="#driving">Driving & Ultrasonic Control</a>
		  <a href="#camera">Camera & Toggle Control</a>
		  <a href="#rgb">RGB & LCD Control</a>
		</div>
		<div id="top" style="font-family:Contrail One;padding-top:65px;min-height:450px;background-repeat:no-repeat;background-size:100%;background-image:url(<%=application.getContextPath()%>/resources/images/coding_background2.jpg);">
			<div style="margin-left:15%;font-size:30px;color:white;">IoTCourse#1.Group 4</div>
			<hr class="bannerhr"/>
			<div style="font-size:50px;color:white;text-align:center;margin-top:80px;">SensingCar Remote Control Board</div>
			<div style="font-size:25px;color:white;text-align:center;margin-top:10px;">IoT Engineer Training Course SpringFramework Project</div>
		</div>

		<div id="bulb" class='wrapper' style="height: 100px; width: 200px; opacity:0;">
			<div class='lightbulb'>
				<div class='head'>
					<div class='wire'></div>
				</div>
				<div class='body'>
					<div class='el'>
						<div class='el__one'></div>
						<div class='el__two'></div>
						<div class='el__three'></div>
						<div class='el__five'></div>
						<div class='el__six'></div>
					</div>
				</div>
			</div>
		</div>

	<div class="container-fluid" style="font-family:Contrail One;width:70%;">
			<div class="row">
			
				<div id="realTimeData" class="col-md-12" style="margin-top:30px;">
					<div>
						<h2 style="color:#000066;">RealTime Data</h2>
						<hr/>
					</div>
					<div class="row" style="position:relative;background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;margin:0px;">
						<div class="row" style="margin:0px;">
							<div id="hoverTemperature" onmouseup="showThermistorSensorChart()" class="col-md-2" style="height:80px;padding-top:10px;">
								<span style="font-size:17px;"><i class="fa fa-thermometer-three-quarters"  aria-hidden="true"></i> Temperature</span>
             					<div id="divTemperature" style="text-align:center;font-size:32px;">0</div>
							</div>
							<div id="hoverDistance" onmouseup="showUltrasonicSensorChart()" class="col-md-2" style="height:80px;padding-top:10px;">
								<span style="font-size:17px;"><i class="fa fa-exchange" aria-hidden="true"></i> Distance</span>
             					<div id="divDistance" style="text-align:center;font-size:32px;">0</div>
							</div>
							<div id="hoverPhoto" onmouseup="showPhotoresistorSensorChart()" class="col-md-2" style="height:80px;padding-top:10px;">
								<span style="font-size:17px;"><i class="fa fa-lightbulb-o" aria-hidden="true"></i> Brightness</span>
             					<div id="divBrightness" style="text-align:center;font-size:32px;">0</div>
							</div>
							<div id="hoverGas" onmouseup="showGasSensorChart()" class="col-md-2" style="height:80px;padding-top:10px;">
								<span style="font-size:17px;"><i class="fa fa-cloud"  aria-hidden="true"></i> Gas Con.</span>
             					<div id="divGas" style="text-align:center;font-size:32px;">0</div>
							</div>
							<div id="hoverTracking" onmouseup="showTrackingSensorChart()" class="col-md-2" style="height:80px;padding-top:10px;">
								<span style="font-size:17px;"><i class="fa fa-adjust" aria-hidden="true"></i> Tracking</span>
             					<div id="divTracking" style="height:30px;background-color:black;margin-top:10px;"></div>
							</div>
							<div onmouseup="showClearSensorChart()" class="col-md-2" style="height:80px;padding-top:10px;">
								<span style="font-size:17px;"><i class="fa fa-window-close" aria-hidden="true"></i> Clear</span>
             					<div id="divClear" style="text-align:center;font-size:16px;"></div>
							</div>
						</div>
					</div>
					<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;margin:0px;">
						<div style="height:320px;">
							<div id="chartContainer" style="height:320px;text-align:center;font-size:30px;color:gray;">
								<br/><br/>Please put your mouse<br/>To the data that you want to see in the chart.
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div id="driving" class="col-md-9" style="margin-bottom:30px;">
						<div>
							<h2 style="color:#000066;">Driving Control</h2>
							<hr/>
						</div>
						<div class="row" style="margin:0px;">
							<div class="col-md-6">
								<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;">
									<div class="col-md-7" style="padding-left:30px;">
										<div id="container-speed" style="height:300px;"></div>
									</div>
									<div class="row" style="margin:0px;text-align:center;height:150px;padding:20px 10px 20px 10px;border-right:2px solid gray;">
										<span style="font-size:24px;padding:10px;"><i class="fa fa-bolt" aria-hidden="true"></i> Direction</span>
	             						<div id="divDirection" style="text-align:center;font-size:48px;">${direction}</div>
									</div>
									<div class="row" style="margin:0px;text-align:center;height:150px;padding:20px;border-right:2px solid gray;">
										<span style="font-size:24px;padding:10px;"><i class="fa fa-bolt" aria-hidden="true"></i> Angle</span>
	             						<div id="divFronttireAngle" style="text-align:center;font-size:50px;">${fronttireAngle}º</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;">
									<div class="col-md-6">
										<div class="row" style="height:100px;padding-top:5px;">
											<div class="col-md-4"></div>
											<div class="col-md-4"><a class="button go" id="backtirego" onclick="backtireDirection('forward','${speed}')"></a></div>
											<div class="col-md-4"></div>
										</div>
										<div class="row" style="height:100px;">
											<div class="col-md-4"><a id="fronttireleft" class="button left" onclick="fronttire('${fronttireAngle}','decrease')"></a></div>
											<div class="col-md-4"></div>
											<div class="col-md-4"><a id="fronttireright" class="button right" onclick="fronttire('${fronttireAngle}','increase')"></a></div>
										</div>
										<div class="row" style="height:100px;padding-bottom:5px;">
											<div class="col-md-4"></div>
											<div class="col-md-4"><a class="button backward" id="backtireback" onclick="backtireDirection('backward','${speed}')"></a></div>
											<div class="col-md-4"></div>
										</div>
									</div>
									<div class="col-md-6" style="padding-left:12%;">
										<div class="row" style="padding:10px;"><a id="backtirespeedup" class="button2 accelerate" onmousedown="speedup('${direction}','${speed}')" onmouseup="stopspeedchange()"></a></div>
										<div class="row" style="padding:10px;"><a id="backtirespeedzero" class="button2 zero" style="height:56px;" onclick="speedzero('${direction}')"></a></div>
										<div class="row" style="padding:10px;"><a id="backtirespeeddown" class="button2 break" onmousedown="speeddown('${direction}','${speed}')" onmouseup="stopspeedchange()"></a></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="ultrasonicangle" class="col-md-3" style="margin-bottom:30px;">
						<div>
							<h2 style="color:#000066;">Ultrasonic Control</h2>
							<hr/>
						</div>
						<div class="col-md-12" style="margin:0px;">
							<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;">
								<div style="height:300px;">
									<div class="col-md-12" style="text-align:center;padding-top:30px;height:150px;margin-bottom:50px;">
										<span style="font-size:36px;padding:10px;"><i class="fa fa-bullseye" aria-hidden="true"></i> US-Angle</span>
          								<div id="ultrasonicleftrightAngle" style="text-align:center;font-size:60px;">${ultrasonicsensorAngle}º</div>
									</div>
									<div class="row" style="margin:0px;text-align:center;height:80px;padding:20px;">
										<i class="fa fa-arrow-left fa-2x" aria-hidden="true" style="display:inline-block;"></i>
										<input id="ultrasonicLeftRight" type="range" min="10" max="170" step="1" 
										   value="${ultrasonicsensorAngle}" onchange="ultrasonicsensor('change',this.value)"
										   style="width:70%;display:inline-block;margin:7px;padding-bottom:5px;"/>
										<i class="fa fa-arrow-right fa-2x" aria-hidden="true" style="display:inline-block;"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div id="camera" class="col-md-8" style="margin-bottom:30px;">
						<div>
							<h2 style="color:#000066;">Camera Control</h2>
							<hr/>
						</div>
						<div class="row" style="margin:0px;">
							<div class="col-md-6">
								<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;">
									<div style="height:300px;text-align:center;padding-top:15px;">
										<img src="${cameraUrl}" style="height:85%;"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row">
									<div class="col-md-12">
										<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;">
											<div style="height:300px;padding-right:25px">
												<div class="row" style="margin:0px;padding-top:15px;">
													<div class="col-md-6" style="text-align:center;border-right:2px solid gray;">
														<span style="font-size:28px;padding:10px;"><i class="fa fa-arrows-h" aria-hidden="true"></i> LR-Angle</span>
             											<div id="leftrightAngle" style="text-align:center;font-size:48px;">${leftright}º</div>
													</div>
													<div class="col-md-6" style="text-align:center;">
														<span style="font-size:28px;padding:10px;"><i class="fa fa-arrows-v" aria-hidden="true"></i> UD-Angle</span>
             											<div id="updownAngle" style="text-align:center;font-size:48px;">${updown}º</div>
													</div>
												</div>
												<div class="row" style="margin:0px;text-align:center;height:80px;padding:20px;">
													<i class="fa fa-arrow-left fa-2x" aria-hidden="true" style="display:inline-block;"></i>
													<input id="cameraLeftRight" type="range" min="10" max="170" step="1" 
													   value="${leftright}" onchange="camera('change',this.value,'${updown}','lr')"
													   style="width:75%;display:inline-block;margin:7px;padding-bottom:5px;"/>
													<i class="fa fa-arrow-right fa-2x" aria-hidden="true" style="display:inline-block;"></i>
												</div>
												<div class="row" style="margin:0px;text-align:center;height:80px;padding:20px;">	
													<i class="fa fa-arrow-down fa-2x" aria-hidden="true" style="display:inline-block;"></i>   
													<input id="cameraUpDown" type="range" min="10" max="100" step="1" 
													   value="${updown}" onchange="camera('change','${leftright}',this.value,'ud')"
													   style="width:75%;display:inline-block;margin:5px;padding-bottom:5px;"/>
													<i class="fa fa-arrow-up fa-2x" aria-hidden="true" style="display:inline-block;"></i>
												</div>
											</div>
										</div>	
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="toggle" class="col-md-4" style="margin-bottom:30px;">
						<div>
							<h2 style="color:#000066;">Toggle Control</h2>
							<hr/>
						</div>
						<div class="col-md-12">
							<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;">
								<div style="height:300px;padding-top:3px;">
									<div class="row">
										<div class="col-md-7" style="text-align:center;padding-top:10px">
											<span style="font-size:30px;padding:10px;"><i class="fa fa-volume-up" aria-hidden="true"></i> Buzzer</span>
             								<div id="divBuzzer" style="text-align:center;font-size:52px;">${buzzerStatus}</div>
										</div>
										<div class="col-md-5">
											<div class="round">
												<input class="inputSwitch" type="checkbox" id="buzzeronoff" name="onoff" onclick="buzzer()" checked="checked"/>
													<div class="back">
														<label class="but" for="buzzeronoff">
															<span class="on">I</span><span class="off">0</span>
														</label>
													<div class="led"></div>
												</div>
											</div>
										</div>
									</div>
									<div class="row" style="margin-top:10px;">
										<div class="col-md-7" style="text-align:center;padding-top:10px">
											<span style="font-size:28px;padding:10px;"><i class="fa fa-bolt" aria-hidden="true"></i> LaserEmitter</span>
             								<div id="divLaser" style="text-align:center;font-size:52px;">${laseremitterStatus}</div>
										</div>
										<div class="col-md-5">
											<div class="round">
												<input class="inputSwitch" type="checkbox" id="laseronoff" name="onoff" onclick="laser()" checked/>
													<div class="back">
														<label class="but" for="laseronoff">
															<span class="on">I</span><span class="off">0</span>
														</label>
													<div class="led"></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div id="lcd" class="col-md-7" style="margin-bottom:30px;float:left;">
					<div>
						<h2 style="color:#000066;">LCD Display Control</h2>
						<hr/>
					</div>
					<div class="col-md-12" style="margin:0px;">
						<div class="row" style="background-color:#ffffff;border:1px solid #f8f8f8;border-radius:5px;">
							<div style="height:240px;">
								<div class="row">
									<div class="col-md-6" style="padding-top:15px;">
										<div class="row" style="margin:0px;height:80px;padding:20px 10px 0px 10px;">
											<span style="font-size:28px;padding:10px;display:inline-block;"><i class="fa fa-bolt" aria-hidden="true"></i> Line0</span>
		             						<div id="outputline0" style="text-align:center;font-size:36px;display:inline-block;">${lcdline0}</div>
										</div>
										<div class="row" style="margin:10px 0px 0px 0px;height:80x;padding:20px 10px 0px 10px;">
											<span style="font-size:28px;padding:10px;display:inline-block;"><i class="fa fa-bolt" aria-hidden="true"></i> Line1</span>
		             						<div id="outputline1" style="text-align:center;font-size:36px;display:inline-block;">${lcdline1}</div>
										</div>
									</div>
									<div class="divlcd col-md-6">
									  <input id="inputline0" class="inputlcd" maxlength="16" placeholder="line0" type="text" required=""/>
									  <input id="inputline1" class="inputlcd" maxlength="16" placeholder="line1" type="text" required=""/>
									  <button class="buttonlcd" onclick="lcd()">Send</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="rgb" style="width:40%;margin-bottom:30px;float:left;">
					<div>
						<h2 style="color:#000066;">RGB LED Control</h2>
						<hr/>
					</div>
					<div style="padding-left:12%">
						<div id="color-palette"></div>
						<div id="color-info"></div>
						<div id="picker" class="block">
							<div class="ui-color-picker" data-topic="picker" data-mode="HSL"></div>
							<div id="picker-samples" sample-id="master"></div>	
							<div id="controls">
								<div id="void-sample" class="icon"></div>
							</div>
						</div>
						<div id="canvas" data-tutorial="drop"></div>
						<span id="redH">${red}</span>
						<span id="greenH">${green}</span>
						<span id="blueH">${blue}</span>
					</div>
				</div>
				
			</div>
		</div>
		<div style="width:100%;background-color:black;text-align:center;margin-top:20px;">
			<div style="height:70px;padding-top:20px;background-color:black;color:white;text-align:center;font-size:18px;">
				© 2017 KOSA. All Rights Reserved | Design by IoTCourse#1.Group4
			</div>
		</div>		
	</body>
</html>