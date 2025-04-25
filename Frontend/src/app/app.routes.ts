import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { HomeComponent } from './home/home.component';
import { authGuard } from './guard/auth.guard';
import { LogsComponent } from './logs/logs.component';
import { PcpoolComponent } from './pcpool/pcpool.component';
import { DevtoolsComponent } from './devtools/devtools.component';

export const routes: Routes = [
    { 
        path:'', 
        redirectTo:'login', 
        pathMatch:'full' 
    },
    {
        path:'login',
        component:LoginComponent
    },
    {
        path:'unauthorized',
        component:UnauthorizedComponent,
        canActivate: [authGuard],
        data: {allowedRoles: ['zimkgrp', 'zimkazubi', 'poolmgmt']}
    },
    {
        path:'home',
        component: HomeComponent,
        canActivate: [authGuard],
        data: {allowedRoles: ['zimkgrp', 'zimkazubi', 'poolmgmt']}
    },
    {
        path: 'logs',
        component: LogsComponent,
        canActivate: [authGuard],
        data: {allowedRoles: ['poolmgmt']}
      },
      {
        path: 'devtools',
        component: DevtoolsComponent,
        canActivate: [authGuard],
        data: {allowedRoles: ['poolmgmt']}
      },
      {
        path: 'pcpool',
        component: PcpoolComponent,
        canActivate: [authGuard],
        data: {allowedRoles: ['zimkgrp', 'zimkazubi', 'poolmgmt']}
      },
      {
        path: '**',
        redirectTo: '/home'
      }
];

export class AppRoutingModule { }