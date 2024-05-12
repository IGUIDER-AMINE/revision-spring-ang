import {ActivatedRouteSnapshot, GuardResult, MaybeAsync, Router, RouterStateSnapshot} from '@angular/router';
import {inject, Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";
import { CanActivateFn } from '@angular/router';

@Injectable({providedIn: 'root'}) // ce Gard doit etre un service pour cela on utilise @Injectable
//{providedIn: 'root'} => on a pas besooin de le d"clarer dans les providers dans file app.modules.ts
export class AuthGuard {
  constructor(private authServices : AuthService,
              private router : Router) {
  }
  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.authServices.isAuthenticated) {
      return true;
    }else {
      this.router.navigateByUrl("/login");
      return false;
    }
  }
}
