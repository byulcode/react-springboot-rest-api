import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {RecoilRoot} from "recoil";
import {BrowserRouter} from "react-router-dom";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <RecoilRoot>
                <App/>
            </RecoilRoot>
        </BrowserRouter>
    </React.StrictMode>,
    document.getElementById('root')
)
;

reportWebVitals();
