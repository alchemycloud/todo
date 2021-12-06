import {Status} from '../../../services/backend/enums';
import {
  ReadTodoRequest,
  ReadTodoResponse,
  TodoApiService,
  UpdateTodoRequest,
  UpdateTodoResponse
} from '../../../services/backend/todoApi.service';
import {StatusDropDown} from '../dropdowns/statusDropDown.dropdown';
import {
  AfterViewInit,
  Component,
  EventEmitter,
  Inject,
  Input,
  OnChanges,
  OnInit,
  Optional,
  Output,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {debounceTime, tap} from 'rxjs/operators';

export class EditTodoModel {
  id: number;
  userId: number;
  task: string;
  date: Date;
  status: Status;

  constructor(id: number,
              userId: number,
              task: string,
              date: Date,
              status: Status) {
    this.id = id;
    this.userId = userId;
    this.task = task;
    this.date = date;
    this.status = status;
  }

}

export class Closed {


  constructor() {

  }

}

@Component({
  selector: 'edit-todo',
  templateUrl: './editTodo.form.html',
  styleUrls: ['./editTodo.form.scss']
})
export class EditTodo implements OnChanges, OnInit, AfterViewInit {
  @Input() id: number = null;
  @ViewChild(StatusDropDown)
  private readonly statusElement: StatusDropDown;
  model: EditTodoModel = new EditTodoModel(null, null, '', null, null);
  @Output() onClosed = new EventEmitter<Closed>();
  submitDisabled = false;
  formGroup: FormGroup;
  userIdControl: FormControl;
  taskControl: FormControl;
  dateControl: FormControl;
  statusControl: FormControl;

  constructor(@Optional() private readonly dialogRef: MatDialogRef<EditTodo>, @Inject(MAT_DIALOG_DATA) private readonly data: any,
              private readonly todoApi: TodoApiService, private readonly fb: FormBuilder) {
    this.id = data.id;
    if (data.onClosed) {
      this.onClosed.subscribe(data.onClosed);
    }
  }

  ngOnInit(): void {
    this.init();
    this.formGroup = this.fb.group({
      userId: new FormControl(this.model.userId, [
        Validators.required,
        Validators.max(9223372036854775807)], []),
      task: new FormControl(this.model.task, [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(255)], []),
      date: new FormControl(this.model.date, [
        Validators.required], []),
      status: new FormControl(this.model.status, [
        Validators.required], [])
    });
    this.userIdControl = this.formGroup.get('userId') as FormControl;
    this.taskControl = this.formGroup.get('task') as FormControl;
    this.dateControl = this.formGroup.get('date') as FormControl;
    this.statusControl = this.formGroup.get('status') as FormControl;

    this.formGroup.statusChanges.pipe(tap(() => {
      // fill the model with new data
      this.model.userId = this.userIdControl.value;
      this.model.task = this.taskControl.value;
      this.model.date = this.dateControl.value;
      this.model.status = this.statusControl.value;
    }), debounceTime(675)).subscribe((newStatus) => {

    });

  }

  ngAfterViewInit(): void {

  }


  ngOnChanges(changes: SimpleChanges): void {
    if (changes.id && !changes.id.firstChange && changes.id.previousValue !== changes.id.currentValue) {
      this.reload(changes.id.currentValue);
    }
  }

  onCloseDialogClick() {
    this.close();
    this.dialogRef.close();
  }

  init() {
    this.load();
  }

  submit() {
    this.submitDisabled = true;
    this.todoApi.updateTodo(new UpdateTodoRequest(this.model.id,
      this.model.userId,
      this.model.task,
      this.model.date,
      this.model.status))
      .subscribe((response: UpdateTodoResponse) => {
        this.submitDisabled = false;
        this.close();
        this.dialogRef.close();
      }, (_) => {
        this.submitDisabled = false;
      });
  }

  load() {
    this.todoApi.readTodo(new ReadTodoRequest(this.id))
      .subscribe((response: ReadTodoResponse) => {
        this.model.id = response.id;
        this.model.userId = response.userId;
        this.model.task = response.task;
        this.model.date = response.date;
        this.model.status = response.status;
        this.formGroup.patchValue(response, {emitEvent: false});
      }, (_) => {
      });
  }

  reload(id: number) {
    this.id = id;
    this.load();
  }

  close() {
    this.onClosed.emit(new Closed());
  }

  onSubmitClick() {
    this.submit();
  }

}

