<nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation" ng-controller="cookieController">
    <div class="container topnav">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand topnav" href="#"><i class="fa fa-book fa-fw"></i>&nbsp;Have You Seen?</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
            	<li>
                    <a href="index.jsp">Home</a>
                </li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Menu <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="showEvents.jsp"><i class="fa fa-list fa-fw"></i>&nbsp;List Events</a></li>
					</ul>
				</li>
                <li ng-hide="existsToken">
                	<a href="login.jsp">Login</a>
                </li>
                <li ng-show="existsToken">
                	<a href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>