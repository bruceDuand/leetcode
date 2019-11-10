# Note

1. assignments in python does not copy object, "=" sign builds a connection between an object and a target, which means here the res.append(temp) let all the objects in res have a connection to the same temp, so they always have the same value. With res.append(temp[:]), the statement makes a copy of temp, and assigns it to be an object in res, which is more reasonable 
