
var Credentials = (function() {
    var User_name = "";
    var User_role = "";
    var User_naam = "";
    var isLogin = false;

    var getIsLogin = function() {
        return isLogin;
    };

    var setIsLogin = function(login) {
        isLogin = login;
    };
  
    var getUser_name = function() {
      return User_name;    // Or pull this from cookie/localStorage
    };
  
    var setUser_name = function(name) {
      User_name = name;     
      // Also set this in cookie/localStorage
    };

    var getUser_role = function() {
        return User_role;
    };

    var setUser_role = function(role) {
        User_role = role;
    };

    var getUser_naam = function() {
        return User_naam;
    };

    var setUser_naam = function(naam) {
        User_naam = naam;
    };
  
    return {
      getUser_name: getUser_name,
      setUser_name: setUser_name,
        getUser_role: getUser_role,
        setUser_role: setUser_role,
        getUser_naam: getUser_naam,
        setUser_naam: setUser_naam,
        getIsLogin: getIsLogin,
        setIsLogin: setIsLogin
    }
  
  })();
  
  export default Credentials;
