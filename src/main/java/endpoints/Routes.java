package endpoints;

public class Routes {

    // بحط ال endpoint اللي بعد اللينك

    public static final String CREATE_USER = "/user";

    // بحط ال param في variable
    public static final String GET_USER = "/user/{username}";

    public static final String UPDATE_USER = "/user/{username}";

    public static final String DELETE_USER = "/user/{username}";


      public static final String  ADD_PET= "/pet";
      public static final String GET_PET= "/pet/{petId}";
      public static final String UPDATE_PET= "/pet";
      public static final String DELETE_PET="/pet/{petId}";

       public static final String GET_Inventory="/store/inventory";
       public static final String PLACE_ORDER="/store/order";
       public static final String GET_ORDER="/store/order/{orderId}";
       public static final String DELETE_ORDER = "/store/order/{orderId}";



}