// import React, { createContext, useReducer } from 'react'

// const types = {
//     LOGIN : 'LOGIN',
//     LOGOUT : 'LOGOUT',
// }

// const loginReducer = (state, action) => {
//     switch(action.type){
//         case types.LOGOUT : {
//             return {
//                 ...state, authenticated : sessionStorage.getItem("access_token") ||
//                 localStorage.getItem("access_token"),
//             }
//         }
//     }
// }

// const initialState = {
//     authenticated : null,

// }

// export const LoginContext = createContext(initialState);

// export const LoginProvider = ({children}) => {
//     const[state, dispatch] = useReducer(loginReducer, initialState)

//     return (
//         <LoginContext.Provider value={{...state, dispatch}}>
//             {children}
//         </LoginContext.Provider>
//     )
// }
