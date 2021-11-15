import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainPageComponent} from './components/main-page/main-page.component';
import {UserLoginComponent} from './components/user-login/user-login.component';
import {AdminLoginComponent} from './components/admin-login/admin-login.component';
import {AdminLandingPageComponent} from './components/admin-landing-page/admin-landing-page.component';
import { ImportExcelComponent } from './components/import-excel/import-excel.component';
import { ManageCompanyComponent } from './components/manage-company/manage-company.component';
import {CreateCompanyComponent} from './components/manage-company/create-company/create-company.component';
import {UserLandingPageComponent} from './components/user-landing-page/user-landing-page.component';
import {IposComponent} from './components/ipos/ipos.component';
import {CreateIpoComponent} from './components/ipos/create-ipo/create-ipo.component';
import { CreateStockExchangeComponent } from './components/stock-exchanges/create-stock-exchange/create-stock-exchange.component';
import { StockExchangesComponent }  from './components/stock-exchanges/stock-exchanges.component';
import { SectorsComponent } from './components/sectors/sectors.component';
import { CreateSectorComponent } from './components/sectors/create-sector/create-sector.component';
import { EditCompanyComponent } from './components/manage-company/edit-company/edit-company.component';
import { UserSignUpComponent } from './components/user-sign-up/user-sign-up.component';
import { ComparisonChartsComponent } from 'src/app/components/comparison-charts/comparison-charts.component';
const routes: Routes =
         [{ path: 'mainpage', component: MainPageComponent},
         { path: 'userlogin', component: UserLoginComponent },
         {path:'adminlogin', component: AdminLoginComponent },
         {path:'adminLandingPage',component:AdminLandingPageComponent},
         {path:'importexcel',component:ImportExcelComponent},
         {path:'managecompany',component:ManageCompanyComponent},
         {path:'createcompany',component:CreateCompanyComponent},
         {path:'userLandingPage',component:UserLandingPageComponent},
         {path:'ipos',component:IposComponent},
          {path:'create-ipo',component:CreateIpoComponent},
          {path: 'stock-exchanges', component: StockExchangesComponent},
           {path: 'create-stock-exchange', component: CreateStockExchangeComponent},
           {path: 'sectors', component: SectorsComponent},
           {path: 'create-sector', component: CreateSectorComponent},
           {path:'edit-company/:Id',component:EditCompanyComponent},
           {path:'signup',component:UserSignUpComponent},
           {path:'comparison-charts',component:ComparisonChartsComponent}
         ]


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
