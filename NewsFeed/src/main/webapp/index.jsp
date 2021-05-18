<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<?xml version="1.0" encoding="UTF-8"?>

<html>
<head>
    <meta charset="UTF-8">
    <title>Create User Page</title>
    <STYLE type="text/css">
    	h1,h2{
    			text-align: center;
    		}
    	nav {
			max-width: 1254px;
		    mask-image: linear-gradient(90deg, rgba(241, 233, 233, 0.01) 0%, #f5eded 25%, #fbf4f4 75%, rgba(241, 235, 235, 0) 100%);
		    margin: 0 auto;
		    padding: 35px 0;
  			}
		nav ul, th {
			text-align: center;
	  		background: linear-gradient(90deg, rgba(245, 235, 235, 0) 0%, rgba(243, 235, 235, 0.2) 25%, rgba(239, 239, 239, 0.2) 75%, rgba(243, 242, 242, 0) 100%);
	  		width: 100%;
	  		box-shadow: 0 0 25px rgba(23, 22, 22, 0.15), inset 0 0 1px rgba(249, 245, 245, 0.63);
			}
		nav ul li {
  			display: inline-block;
			}

		nav ul li a {
			 padding: 18.9px;
			 font-family: "Roboto";
			 color: rgba(8, 8, 8, 0.66);
			 text-shadow: 1px 1px 1px rgba(247, 241, 241, 0.53);
			 font-size: 25px;
			 text-decoration: none;
			 display: block;
			 }
    </STYLE>
</head>
<body>
	<nav>
		<ul>		
			<%session = request.getSession();
				if (session.getAttribute("Login")!=null){
					out.println("<li><a href='/NewsFeed/first/addnews'>Add News</a></li>");
					out.println("<li><a name='poisk' href='/NewsFeed/first/exit'>Exit</a></li>");
				}
				else{
					out.println("<li><a href='first/registration'>Authorization</a></li>");
				}%>	
			<li><a href="first/newscontent"> News Feed</a></li>
			<!-- <li><a href="first/registration">Authorization</a></li> -->
		</ul>
	</nav>
<h1>Доро пожаловать в мир свежих новостей о наших милых животных!!!</h1>
<h2>Hello World!!!</h2>
<h2>Данный ресурс создан в учебных целях, поэтому он точно будет необычным и возможно полезен именно своей нестандартностью!</h2>
<h1><img width="800" src="/NewsFeed/URLToReachResourcesFolder/images/181image.png" alt="Problems"></h1>
</body>
</html>
