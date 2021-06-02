import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSliderModule } from '@angular/material/slider';
import {MatGridListModule} from '@angular/material/grid-list';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { MatTabsModule,} from '@angular/material/tabs';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatIconModule} from '@angular/material/icon';
import { RegisterComponent } from './register/register.component';
import { RouterModule, Routes } from '@angular/router';
import { RouterService } from './services/router.service';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import  {MatToolbarModule } from '@angular/material/toolbar';
import { SearchComponent } from './search/search.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { GifService } from './services/gif.service';
import { GifguardGuard } from './gifguard.guard';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AllfeedbackComponent } from './allfeedback/allfeedback.component';
import { FeedbackComponent } from './feedback/feedback.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { FooterComponent } from './footer/footer.component';
import { LoginService } from './services/login.service';
import { NavbarModule, WavesModule, ButtonsModule, MdbIconComponent } from 'angular-bootstrap-md'
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { UpdateUserProfileComponent } from './update-user-profile/update-user-profile.component';
import{ MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MdbModule } from 'mdb-angular-ui-kit';

const appRoutes: Routes = [
  { path: 'login',
    component: LoginComponent 
   },
   { path: 'register',
    component: RegisterComponent
   },
   { path: 'update',
    component: UpdateUserProfileComponent,
    canActivate: [GifguardGuard]
  
   },
   {
     path: '',
     redirectTo: '/home',
     pathMatch: 'full' 
   },
   {
     path: 'home',
     component: HomeComponent
   },
   {
    path: 'favorites',
    component: FavouriteComponent,
    canActivate: [GifguardGuard]
  },
  {
     path: 'feedback',
     component: FeedbackComponent,
     canActivate: [GifguardGuard]
  },
  {
    path: 'allfeedback',
    component: AllfeedbackComponent,
    canActivate: [GifguardGuard]
  },

];
  
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    HeaderComponent,
    SearchComponent,
    FavouriteComponent,
    FeedbackComponent,
    FavouriteComponent,
    AllfeedbackComponent,
    FooterComponent,
    UpdateUserProfileComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatSliderModule,
    FormsModule,
    MatInputModule,
    NavbarModule,
    ButtonsModule,
    WavesModule,
    MDBBootstrapModule.forRoot(),
    MatCardModule,
    MatTabsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    MatGridListModule,
    MatToolbarModule,
    NgxPaginationModule,
    MatSidenavModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MdbModule,
    NoopAnimationsModule

  ],
  providers: [RouterService,GifService,GifguardGuard,LoginService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
