// console.clear();

let contentTitle;

console.log(document.cookie);
function dynamicClothingSection(route) {
  // var realRoute = Java.type('src.Entity.Route');
  // realRoute = route;
  // var rid = realRoute.getIdRoute();
  // var rname = realRoute.getRouteName();
  // var loc = realRoute.getLocation();
  // var des = realRoute.getDescription();

  let boxDiv = document.createElement("div");
  boxDiv.id = "box";

  let boxLink = document.createElement("a");
  // boxLink.href = '#'
  boxLink.href = "/routeInfo.jsp" + route.getIdRoute();
  // console.log('link=>' + boxLink);


  let detailsDiv = document.createElement("div");
  detailsDiv.id = "details";

  let h3 = document.createElement("h3");
  let description = document.createTextNode(route.id);
  h3.appendChild(description);

  let h4 = document.createElement("h4");
  let location = document.createTextNode(route.getLocation());
  h4.appendChild(location);

  let h2 = document.createElement("h2");
  let rName = document.createTextNode("rs  " + route.getRouteName());
  h2.appendChild(rName);

  boxDiv.appendChild(boxLink);
  boxLink.appendChild(detailsDiv);
  detailsDiv.appendChild(h3);
  detailsDiv.appendChild(h4);
  detailsDiv.appendChild(h2);

  return boxDiv;
}

//  TO SHOW THE RENDERED CODE IN CONSOLE
// console.log(dynamicClothingSection());

// console.log(boxDiv)

let mainContainer = document.getElementById("mainContainer");
let containerResult = document.getElementById("containerResult");
let containerAccessories = document.getElementById("containerAccessories");
// mainContainer.appendChild(dynamicClothingSection('hello world!!'))

// BACKEND CALLING



let httpRequest = new XMLHttpRequest();

httpRequest.onreadystatechange = function() {
  if (this.readyState === 4) {
    if (this.status == 200) {
      // console.log('call successful');
      //let routes =
      //contentTitle: "<%=routes%>";
      contentTitle = JSON.parse(this.responseText);

      if (document.cookie.indexOf(",counter=") >= 0) {
        var counter = document.cookie.split(",")[1].split("=")[1];
        document.getElementById("badge").innerHTML = counter;
      }
      for (let i = 0; i < contentTitle.length; i++) {
          console.log(contentTitle[i]);
          containerResult.appendChild(
            dynamicClothingSection(contentTitle[i])
          );
      }
    } else {
      console.log("call failed!");
    }
  }
};
httpRequest.open(
  "GET",
  "https://5d76bf96515d1a0014085cf9.mockapi.io/product",
  true
);
httpRequest.send();
