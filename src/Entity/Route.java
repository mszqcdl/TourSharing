package Entity;



public class Route {
    private String idRoute;
    private String routeName;
    private String location;
    private String idUser;
    private String description;

    public Route(String idRoute, String routeName, String location, String idUser, String description) {
        this.idRoute = idRoute;
        this.routeName = routeName;
        this.location = location;
        this.idUser = idUser;
        this.description = description;
    }

    public String getIdRoute() {
        return idRoute;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getLocation() {
        return location;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdRoute(String idRoute) {
        this.idRoute = idRoute;
    }

    @Override
    public String toString() {
        return "Route{" +
                "idRoute='" + idRoute + '\'' +
                ", routeName='" + routeName + '\'' +
                ", location='" + location + '\'' +
                ", idUser='" + idUser + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
