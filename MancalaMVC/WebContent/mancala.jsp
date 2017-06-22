<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Mancala</title>
		<link rel="stylesheet" href="mancala.css" type="text/css"/>
	</head>
	<body>
		<% int[] waardesBakjes = (int[]) request.getAttribute("waardesBakjes"); %>
		<% String boodschapNaarGebruiker = (String) request.getAttribute("boodschapNaarGebruiker"); %>
		
		   	<div id="mancala_bord">
				<div id="invulling_bord">
					<div class="mancala_borddeel" id="kalaha_speler2">
						<p id="num_kalaha_speler2">
							<% out.print(waardesBakjes[13]); %>
						</p>
					</div>
					
					<div class="mancala_borddeel" id="kalaha_speler1">
						<p id="num_kalaha_speler1">
							<% out.print(waardesBakjes[6]); %>
						</p>
					</div>
	
					<form method="POST" action="Mancala.play">
						<div class="mancala_borddeel" id="kleine_bakjes">
							
							<div id="bakjes_speler2">
								<button class="bakje_speler2" id="bakje6_speler2" type="submit" name="bakje" value="12">
									<p class="num_klein_bakje" id="num_bakje1_speler2">
										<% out.print(waardesBakjes[12]); %>
									</p>
								</button>
								
								<button class="bakje_speler2" id="bakje5_speler2"  type="submit" name="bakje" value="11">
									<p class="num_klein_bakje" id="num_bakje2_speler2">
										<% out.print(waardesBakjes[11]); %>
									</p>
								</button>
								
								<button class="bakje_speler2" id="bakje4_speler2" type="submit" name="bakje" value="10">
									<p class="num_klein_bakje" id="num_bakje3_speler2">
										<% out.print(waardesBakjes[10]); %>
									</p>
								</button>
								
								<button class="bakje_speler2" id="bakje3_speler2" type="submit" name="bakje" value="9">
									<p class="num_klein_bakje" id="num_bakje4_speler2">
										<% out.print(waardesBakjes[9]); %>
									</p>
								</button>
								<button class="bakje_speler2" id="bakje2_speler2" type="submit" name="bakje" value="8">
									<p class="num_klein_bakje" id="num_bakje5_speler2">
										<% out.print(waardesBakjes[8]); %>
									</p>
								</button>
								<button class="bakje_speler2" id="bakje1_speler2" type="submit" name="bakje" value="7">
									<p class="num_klein_bakje" id="num_bakje6_speler2">
										<% out.print(waardesBakjes[7]); %>
									</p>
								</button>
							</div>
							<p class="naam_speler">Speler2</p>
							<p class="naam_speler">Speler1</p>
							<div id="bakjes_speler1">
								<button class="bakje_speler1" id="bakje1_speler1" type="submit" name="bakje" value="1">
									<p class="num_klein_bakje" id="num_bakje1_speler1">
										<% out.print(waardesBakjes[0]); %>
									</p>
								</button>
								<button class="bakje_speler1" id="bakje2_speler1" type="submit" name="bakje" value="2">
									<p class="num_klein_bakje" id="num_bakje2_speler1">
										<% out.print(waardesBakjes[1]); %>
									</p>
								</button>
								<button class="bakje_speler1" id="bakje3_speler1" type="submit" name="bakje" value="3">
									<p class="num_klein_bakje" id="num_bakje3_speler1">
										<% out.print(waardesBakjes[2]); %>
									</p>
								</button>
								<button class="bakje_speler1" id="bakje4_speler1" type="submit" name="bakje" value="4">
									<p class="num_klein_bakje" id="num_bakje4_speler1">
										<% out.print(waardesBakjes[3]); %>
									</p>
								</button>
								<button class="bakje_speler1" id="bakje5_speler1" type="submit" name="bakje" value="5">
									<p class="num_klein_bakje" id="num_bakje5_speler1">
										<% out.print(waardesBakjes[4]); %>
									</p>
								</button>
								<button class="bakje_speler1" id="bakje6_speler1" type="submit" name="bakje" value="6">
									<p class="num_klein_bakje" id="num_bakje6_speler1">
										<% out.print(waardesBakjes[5]); %>
									</p>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<h1 id="boodschapNaarGebruiker"><% out.print(boodschapNaarGebruiker); %></h1>
			<form method="POST" action="Mancala.play">
				<button id="knopNieuwSpel" type="submit" name="nieuwSpel" value="nieuwSpel">Start nieuw spel</button>
			</form>
		
	</body>
</html>