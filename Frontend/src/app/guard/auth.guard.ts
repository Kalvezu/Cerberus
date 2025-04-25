import { CanActivateFn, Router } from '@angular/router';
import { inject } from "@angular/core";
import { AuthService } from "../services/auth.service";

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isLoggedIn()) {
    const users = authService.getUser();
    const allowedRoles = route?.data?.['allowedRoles'];

    if (allowedRoles && users) {
      const userRoles = users.userRole;
      const hasRole = allowedRoles.some((role: string) => userRoles.includes(role));
      if (hasRole) {
        return true;
      } else {
        router.navigate(['/unauthorized']);
        return false;
      }
    }

    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }

  
};
