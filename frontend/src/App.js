import React , { useState } from 'react';
import logo from './logo.svg';
import './App.css';
import SimpleBottomNavigation from './Components/NavigationBar'
import Divider from '@material-ui/core/Divider';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import { ChuyingTestPage } from './Pages/Chuying'
import { JiayiTestPage } from './Pages/Jiayi'
import { JayTestPage } from './Pages/Jay'
export function App() {

  const redirectChuying =() => {
    window.location.href = '/Chuying'
  }

  const redirectJiayi =() => {
    window.location.href = '/Jiayi'
  }

  const redirectJay =() => {
    window.location.href ='/Jay'
  }

  return (
    <div className="App">
     <Router>
       <SimpleBottomNavigation redirectChuying = { redirectChuying } redirectJay = {redirectJay} redirectJiayi ={ redirectJiayi}/>
        <Divider/>
       <Switch>
       <Route path = "/Chuying">
         <ChuyingTestPage/>
       </Route>
       <Route path = "/Jiayi">
        <JiayiTestPage/>
       </Route>
       <Route path = '/Jay'>
         <JayTestPage/>
      </Route>
     </Switch>
     </Router>
     
    </div>
  );
}

