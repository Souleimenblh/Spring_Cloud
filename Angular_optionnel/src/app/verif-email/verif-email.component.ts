import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-verif-email',
  templateUrl: './verif-email.component.html',
})
export class VerifEmailComponent implements OnInit {
  code: string = '';
  user: User = new User();
  err = '';

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.user = this.authService.regitredUser;
  }

  onValidateEmail() {
    this.authService.validateEmail(this.code).subscribe({
      next: (res) => {
        alert('Login successful');
        this.authService.login(this.user).subscribe({
          next: (data) => {
            let jwToken = data.headers.get('Authorization')!;
            this.authService.saveToken(jwToken);
            this.router.navigate(['/']);
          },
          error: (err: any) => {
            console.log(err);
          },
        });
      },
      error: (err: any) => {
        if (err.error.errorCode == 'INVALID_TOKEN')
          this.err = "Votre code n'est pas valide !";

        if (err.error.errorCode == 'EXPIRED_TOKEN')
          this.err = 'Votre code a expiré !';
      },
    });
  }
}
