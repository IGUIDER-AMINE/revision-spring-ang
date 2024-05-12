import {ActivatedRouteSnapshot, GuardResult, MaybeAsync, Router, RouterStateSnapshot} from '@angular/router';
import {inject, Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";
import { CanActivateFn } from '@angular/router';

@Injectable({providedIn: 'root'}) // ce Gard doit etre un service pour cela on utilise @Injectable
//{providedIn: 'root'} => on a pas besooin de le d"clarer dans les providers dans file app.modules.ts
export class AuthorizationGuard {
  constructor(private authServices : AuthService,
              private router : Router) {
  }
  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.authServices.isAuthenticated) {
      let requiredRoles = route.data['roles'];
      let userRoles = this.authServices.roles;
      for(let role of userRoles){
        if(requiredRoles.includes(role)){
          return true
        }
      }
      return false;
    }else {
      this.router.navigateByUrl("/login");
      return false;
    }
  }
}
