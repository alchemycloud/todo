import {UserRole} from '../../../services/backend/enums';
import {CreateUserRequest, CreateUserResponse, UserApiService} from '../../../services/backend/userApi.service';
import {UserRoleDropDown} from '../dropdowns/userRoleDropDown.dropdown';
import {
  AfterViewInit,
  Component,
  EventEmitter,
  Inject,
  Input,
  OnInit,
  Optional,
  Output,
  ViewChild
} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {debounceTime, tap} from 'rxjs/operators';

export class CreateUserModel {
  firstName: string;
  lastName: string;
  role: UserRole;
  username: string;
  passwordHash: string;

  constructor(firstName: string,
              lastName: string,
              role: UserRole,
              username: string,
              passwordHash: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.username = username;
    this.passwordHash = passwordHash;
  }

}

export class Closed {


  constructor() {

  }

}

@Component({
  selector: 'create-user',
  templateUrl: './createUser.form.html',
  styleUrls: ['./createUser.form.scss']
})
export class CreateUser implements OnInit, AfterViewInit {
  @Input() model: CreateUserModel = new CreateUserModel('', '', null, '', '');
  @ViewChild(UserRoleDropDown)
  private readonly roleElement: UserRoleDropDown;
  @Output() onClosed = new EventEmitter<Closed>();
  submitDisabled = false;
  formGroup: FormGroup;
  firstNameControl: FormControl;
  lastNameControl: FormControl;
  roleControl: FormControl;
  usernameControl: FormControl;
  passwordHashControl: FormControl;

  constructor(@Optional() private readonly dialogRef: MatDialogRef<CreateUser>, @Inject(MAT_DIALOG_DATA) private readonly data: any,
              private readonly userApi: UserApiService, private readonly fb: FormBuilder) {
    if (data.model != null) {
      this.model = data.model;
    }
    if (data.onClosed) {
      this.onClosed.subscribe(data.onClosed);
    }
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
      role: new FormControl(this.model.role, [
        Validators.required], []),
      username: new FormControl(this.model.username, [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(128)], []),
      passwordHash: new FormControl(this.model.passwordHash, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(128)], [])
    });
    this.firstNameControl = this.formGroup.get('firstName') as FormControl;
    this.lastNameControl = this.formGroup.get('lastName') as FormControl;
    this.roleControl = this.formGroup.get('role') as FormControl;
    this.usernameControl = this.formGroup.get('username') as FormControl;
    this.passwordHashControl = this.formGroup.get('passwordHash') as FormControl;

    this.formGroup.statusChanges.pipe(tap(() => {
      // fill the model with new data
      this.model.firstName = this.firstNameControl.value;
      this.model.lastName = this.lastNameControl.value;
      this.model.role = this.roleControl.value;
      this.model.username = this.usernameControl.value;
      this.model.passwordHash = this.passwordHashControl.value;
    }), debounceTime(675)).subscribe((newStatus) => {

    });

  }

  ngAfterViewInit(): void {

  }


  onCloseDialogClick() {
    this.close();
    this.dialogRef.close();
  }

  init() {

  }

  submit() {
    this.submitDisabled = true;
    this.userApi.createUser(new CreateUserRequest(this.model.firstName,
      this.model.lastName,
      this.model.role,
      this.model.username,
      this.model.passwordHash))
      .subscribe((response: CreateUserResponse) => {
        this.submitDisabled = false;
        this.close();
        this.dialogRef.close();
      }, (_) => {
        this.submitDisabled = false;
      });
  }

  close() {
    this.onClosed.emit(new Closed());
  }

  onSubmitClick() {
    this.submit();
  }

}

