import {AuthenticationApiService, SignUpRequest} from '../../../services/backend/authenticationApi.service';
import {SessionService} from '../../../services/session.service';
import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {debounceTime, tap} from 'rxjs/operators';

export class SignUpFormModel {
  firstName: string;
  lastName: string;
  username: string;
  password: string;

  constructor(firstName: string,
              lastName: string,
              username: string,
              password: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

}

@Component({
  selector: 'sign-up-form',
  templateUrl: './signUpForm.form.html',
  styleUrls: ['./signUpForm.form.scss']
})
export class SignUpForm implements OnInit, AfterViewInit {
  @Input() model: SignUpFormModel = new SignUpFormModel('', '', '', '');
  submitDisabled = false;
  formGroup: FormGroup;
  firstNameControl: FormControl;
  lastNameControl: FormControl;
  usernameControl: FormControl;
  passwordControl: FormControl;

  constructor(private readonly authenticationApi: AuthenticationApiService, private readonly sessionService: SessionService,
              private readonly router: Router, private readonly fb: FormBuilder) {

  }

  ngOnInit(): void {
    this.init();
    this.formGroup = this.fb.group({
      firstName: new FormControl(this.model.firstName, [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(40)], []),
      lastName: new FormControl(this.model.lastName, [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(60)], []),
      username: new FormControl(this.model.username, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(128)], []),
      password: new FormControl(this.model.password, [
        Validators.required,
        Validators.minLength(12),
        Validators.maxLength(128),
        Validators.pattern(/^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){12,128}$/)], [])
    });
    this.firstNameControl = this.formGroup.get('firstName') as FormControl;
    this.lastNameControl = this.formGroup.get('lastName') as FormControl;
    this.usernameControl = this.formGroup.get('username') as FormControl;
    this.passwordControl = this.formGroup.get('password') as FormControl;

    this.formGroup.statusChanges.pipe(tap(() => {
      // fill the model with new data
      this.model.firstName = this.firstNameControl.value;
      this.model.lastName = this.lastNameControl.value;
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
    this.authenticationApi.signUp(new SignUpRequest(this.model.firstName,
      this.model.lastName,
      this.model.username,
      this.model.password))
      .subscribe(() => {
        this.submitDisabled = false;
        this.sessionService.clear();
        this.router.navigate(['/sign-in']);
      }, (_) => {
        this.submitDisabled = false;
      });
  }

  onSubmitClick() {
    this.submit();
  }

}

