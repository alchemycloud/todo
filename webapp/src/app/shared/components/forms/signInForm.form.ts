import {
  AuthenticationApiService,
  SignInRequest,
  SignInResponse
} from '../../../services/backend/authenticationApi.service';
import {UserRole} from '../../../services/backend/enums';
import {SessionData, SessionService} from '../../../services/session.service';
import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {debounceTime, tap} from 'rxjs/operators';

export class SignInFormModel {
  username: string;
  password: string;

  constructor(username: string,
              password: string) {
    this.username = username;
    this.password = password;
  }

}

@Component({
  selector: 'sign-in-form',
  templateUrl: './signInForm.form.html',
  styleUrls: ['./signInForm.form.scss']
})
export class SignInForm implements OnInit, AfterViewInit {
  @Input() model: SignInFormModel = new SignInFormModel('', '');
  submitDisabled = false;
  formGroup: FormGroup;
  usernameControl: FormControl;
  passwordControl: FormControl;

  constructor(private readonly authenticationApi: AuthenticationApiService, private readonly sessionService: SessionService,
              private readonly router: Router, private readonly fb: FormBuilder) {

  }

  ngOnInit(): void {
    this.init();
    this.formGroup = this.fb.group({
      username: new FormControl(this.model.username, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(128)], []),
      password: new FormControl(this.model.password, [
        Validators.required,
        Validators.minLength(12),
        Validators.maxLength(128)], [])
    });
    this.usernameControl = this.formGroup.get('username') as FormControl;
    this.passwordControl = this.formGroup.get('password') as FormControl;

    this.formGroup.statusChanges.pipe(tap(() => {
      // fill the model with new data
      this.model.username = this.usernameControl.value;
      this.model.password = this.passwordControl.value;
    }), debounceTime(675)).subscribe((newStatus) => {

    });

  }

  ngAfterViewInit(): void {

  }


  init() {

  }

  submit() {
    this.submitDisabled = true;
    this.authenticationApi.signIn(new SignInRequest(this.model.username,
      this.model.password))
      .subscribe((response: SignInResponse) => {
      this.sessionService.save(new SessionData(response.accessToken, response.refreshToken, response.id, response.firstName, response.lastName,
          response.role, response.username));
        this.submitDisabled = false;
        if (response.role === UserRole.ADMIN || response.role === UserRole.MEMBER) {
          this.router.navigate(['/todos']);
        }
      }, (_) => {
        this.submitDisabled = false;
      });
  }

  onSubmitClick() {
    this.submit();
  }

}

