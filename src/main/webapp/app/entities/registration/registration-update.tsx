import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
import { getEntity, getJerkEntity, updateJerkEntity, updateEntity, createEntity, reset } from './registration.reducer';
import { IJerk } from 'app/shared/model/jerk.model';
import { IRegistration } from 'app/shared/model/registration.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { cleanEntity } from 'app/shared/util/entity-utils';

export interface IRegistrationUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IRegistrationUpdateState {
  isNew: boolean;
}

export class RegistrationUpdate extends React.Component<IRegistrationUpdateProps, IRegistrationUpdateState> {
  constructor(props) {
    super(props);

    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
      this.props.getJerkEntity(this.props.match.params['jerkId']);
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { jerkEntity, registrationEntity } = this.props;
      const entity = {
        ...registrationEntity,
        ...values
      };

      let _registion = cleanEntity(entity);
      let _entity = { ...jerkEntity, ...{ jerkInfo: _registion } };
      if (this.state.isNew) {
        console.log(_entity);
        this.props.updateJerkEntity(_entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/registration');
  };

  render() {
    const { registrationEntity, jerkEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jerkkApp.registration.home.createOrEditLabel">
              <Translate contentKey="jerkkApp.registration.home.createOrEditLabel">Create or edit a Registration</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : registrationEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="registration-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="registTypeLabel" for="registType">
                    <Translate contentKey="jerkkApp.registration.registType">Regist Type</Translate>
                  </Label>
                  <AvField
                    id="registration-registType"
                    type="string"
                    className="form-control"
                    name="registType"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dwqcLabel" for="dwqc">
                    <Translate contentKey="jerkkApp.registration.dwqc">Dwqc</Translate>
                  </Label>
                  <AvField
                    id="registration-dwqc"
                    type="text"
                    name="dwqc"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="hxcpmcLabel" for="hxcpmc">
                    <Translate contentKey="jerkkApp.registration.hxcpmc">Hxcpmc</Translate>
                  </Label>
                  <AvField
                    id="registration-hxcpmc"
                    type="text"
                    name="hxcpmc"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 120, errorMessage: translate('entity.validation.maxlength', { max: 120 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="zztjdwLabel" for="zztjdw">
                    <Translate contentKey="jerkkApp.registration.zztjdw">Zztjdw</Translate>
                  </Label>
                  <AvField id="registration-zztjdw" type="text" name="zztjdw" />
                </AvGroup>
                <AvGroup>
                  <Label id="dwhgrdzLabel" for="dwhgrdz">
                    <Translate contentKey="jerkkApp.registration.dwhgrdz">Dwhgrdz</Translate>
                  </Label>
                  <AvField
                    id="registration-dwhgrdz"
                    type="text"
                    name="dwhgrdz"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="szqylxLabel" for="szqylx">
                    <Translate contentKey="jerkkApp.registration.szqylx">Szqylx</Translate>
                  </Label>
                  <AvField
                    id="registration-szqylx"
                    type="text"
                    name="szqylx"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sslyLabel" for="ssly">
                    <Translate contentKey="jerkkApp.registration.ssly">Ssly</Translate>
                  </Label>
                  <AvField
                    id="registration-ssly"
                    type="text"
                    name="ssly"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="gscpjjLabel" for="gscpjj">
                    <Translate contentKey="jerkkApp.registration.gscpjj">Gscpjj</Translate>
                  </Label>
                  <AvField
                    id="registration-gscpjj"
                    type="text"
                    name="gscpjj"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 360, errorMessage: translate('entity.validation.maxlength', { max: 360 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="mbkhscLabel" for="mbkhsc">
                    <Translate contentKey="jerkkApp.registration.mbkhsc">Mbkhsc</Translate>
                  </Label>
                  <AvField
                    id="registration-mbkhsc"
                    type="text"
                    name="mbkhsc"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dqzykhLabel" for="dqzykh">
                    <Translate contentKey="jerkkApp.registration.dqzykh">Dqzykh</Translate>
                  </Label>
                  <AvField id="registration-dqzykh" type="text" name="dqzykh" />
                </AvGroup>
                <AvGroup>
                  <Label id="gnwhjjxLabel" for="gnwhjjx">
                    <Translate contentKey="jerkkApp.registration.gnwhjjx">Gnwhjjx</Translate>
                  </Label>
                  <AvField
                    id="registration-gnwhjjx"
                    type="text"
                    name="gnwhjjx"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="zljsLabel">
                    <Translate contentKey="jerkkApp.registration.zljs">Zljs</Translate>
                  </Label>
                  <AvInput
                    id="registration-zljs"
                    type="select"
                    className="form-control"
                    name="zljs"
                    value={(!isNew && registrationEntity.zljs) || 'YES'}
                  >
                    <option value="YES">
                      <Translate contentKey="jerkkApp.Decision.YES" />
                    </option>
                    <option value="NO">
                      <Translate contentKey="jerkkApp.Decision.NO" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="hxjslyLabel">
                    <Translate contentKey="jerkkApp.registration.hxjsly">Hxjsly</Translate>
                  </Label>
                  <AvInput
                    id="registration-hxjsly"
                    type="select"
                    className="form-control"
                    name="hxjsly"
                    value={(!isNew && registrationEntity.hxjsly) || 'S1'}
                  >
                    <option value="S1">
                      <Translate contentKey="jerkkApp.HxjslyEnum.S1" />
                    </option>
                    <option value="S2">
                      <Translate contentKey="jerkkApp.HxjslyEnum.S2" />
                    </option>
                    <option value="S3">
                      <Translate contentKey="jerkkApp.HxjslyEnum.S3" />
                    </option>
                    <option value="S4">
                      <Translate contentKey="jerkkApp.HxjslyEnum.S4" />
                    </option>
                    <option value="OTHER">
                      <Translate contentKey="jerkkApp.HxjslyEnum.OTHER" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="kjcgzhLabel">
                    <Translate contentKey="jerkkApp.registration.kjcgzh">Kjcgzh</Translate>
                  </Label>
                  <AvInput
                    id="registration-kjcgzh"
                    type="select"
                    className="form-control"
                    name="kjcgzh"
                    value={(!isNew && registrationEntity.kjcgzh) || 'S1'}
                  >
                    <option value="S1">
                      <Translate contentKey="jerkkApp.KjcgzhEnum.S1" />
                    </option>
                    <option value="S2">
                      <Translate contentKey="jerkkApp.KjcgzhEnum.S2" />
                    </option>
                    <option value="S3">
                      <Translate contentKey="jerkkApp.KjcgzhEnum.S3" />
                    </option>
                    <option value="OTHER">
                      <Translate contentKey="jerkkApp.KjcgzhEnum.OTHER" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="jmlyqkLabel">
                    <Translate contentKey="jerkkApp.registration.jmlyqk">Jmlyqk</Translate>
                  </Label>
                  <AvInput
                    id="registration-jmlyqk"
                    type="select"
                    className="form-control"
                    name="jmlyqk"
                    value={(!isNew && registrationEntity.jmlyqk) || 'K1'}
                  >
                    <option value="K1">
                      <Translate contentKey="jerkkApp.JmlyqkEnum.K1" />
                    </option>
                    <option value="K2">
                      <Translate contentKey="jerkkApp.JmlyqkEnum.K2" />
                    </option>
                    <option value="K3">
                      <Translate contentKey="jerkkApp.JmlyqkEnum.K3" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="jscsdLabel" for="jscsd">
                    <Translate contentKey="jerkkApp.registration.jscsd">Jscsd</Translate>
                  </Label>
                  <AvField
                    id="registration-jscsd"
                    type="text"
                    name="jscsd"
                    validate={{
                      maxLength: { value: 120, errorMessage: translate('entity.validation.maxlength', { max: 120 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="jzmsylqkLabel" for="jzmsylqk">
                    <Translate contentKey="jerkkApp.registration.jzmsylqk">Jzmsylqk</Translate>
                  </Label>
                  <AvField
                    id="registration-jzmsylqk"
                    type="text"
                    name="jzmsylqk"
                    validate={{
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="jzysjsLabel" for="jzysjs">
                    <Translate contentKey="jerkkApp.registration.jzysjs">Jzysjs</Translate>
                  </Label>
                  <AvField
                    id="registration-jzysjs"
                    type="text"
                    name="jzysjs"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fzrdhLabel" for="fzrdh">
                    <Translate contentKey="jerkkApp.registration.fzrdh">Fzrdh</Translate>
                  </Label>
                  <AvField
                    id="registration-fzrdh"
                    type="text"
                    name="fzrdh"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xbLabel">
                    <Translate contentKey="jerkkApp.registration.xb">Xb</Translate>
                  </Label>
                  <AvInput
                    id="registration-xb"
                    type="select"
                    className="form-control"
                    name="xb"
                    value={(!isNew && registrationEntity.xb) || 'MAN'}
                  >
                    <option value="MAN">
                      <Translate contentKey="jerkkApp.XbEnum.MAN" />
                    </option>
                    <option value="WOMAN">
                      <Translate contentKey="jerkkApp.XbEnum.WOMAN" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lxfsLabel" for="lxfs">
                    <Translate contentKey="jerkkApp.registration.lxfs">Lxfs</Translate>
                  </Label>
                  <AvField
                    id="registration-lxfs"
                    type="text"
                    name="lxfs"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 36, errorMessage: translate('entity.validation.maxlength', { max: 36 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="jerkkApp.registration.email">Email</Translate>
                  </Label>
                  <AvField
                    id="registration-email"
                    type="text"
                    name="email"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 36, errorMessage: translate('entity.validation.maxlength', { max: 36 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fzrnlLabel">
                    <Translate contentKey="jerkkApp.registration.fzrnl">Fzrnl</Translate>
                  </Label>
                  <AvInput
                    id="registration-fzrnl"
                    type="select"
                    className="form-control"
                    name="fzrnl"
                    value={(!isNew && registrationEntity.fzrnl) || 'A1'}
                  >
                    <option value="A1">
                      <Translate contentKey="jerkkApp.AgeEnum.A1" />
                    </option>
                    <option value="A2">
                      <Translate contentKey="jerkkApp.AgeEnum.A2" />
                    </option>
                    <option value="A3">
                      <Translate contentKey="jerkkApp.AgeEnum.A3" />
                    </option>
                    <option value="A4">
                      <Translate contentKey="jerkkApp.AgeEnum.A4" />
                    </option>
                    <option value="A5">
                      <Translate contentKey="jerkkApp.AgeEnum.A5" />
                    </option>
                    <option value="A6">
                      <Translate contentKey="jerkkApp.AgeEnum.A6" />
                    </option>
                    <option value="A7">
                      <Translate contentKey="jerkkApp.AgeEnum.A7" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="tdpjnlLabel">
                    <Translate contentKey="jerkkApp.registration.tdpjnl">Tdpjnl</Translate>
                  </Label>
                  <AvInput
                    id="registration-tdpjnl"
                    type="select"
                    className="form-control"
                    name="tdpjnl"
                    value={(!isNew && registrationEntity.tdpjnl) || 'A1'}
                  >
                    <option value="A1">
                      <Translate contentKey="jerkkApp.AgeEnum.A1" />
                    </option>
                    <option value="A2">
                      <Translate contentKey="jerkkApp.AgeEnum.A2" />
                    </option>
                    <option value="A3">
                      <Translate contentKey="jerkkApp.AgeEnum.A3" />
                    </option>
                    <option value="A4">
                      <Translate contentKey="jerkkApp.AgeEnum.A4" />
                    </option>
                    <option value="A5">
                      <Translate contentKey="jerkkApp.AgeEnum.A5" />
                    </option>
                    <option value="A6">
                      <Translate contentKey="jerkkApp.AgeEnum.A6" />
                    </option>
                    <option value="A7">
                      <Translate contentKey="jerkkApp.AgeEnum.A7" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="gjrcsLabel" for="gjrcs">
                    <Translate contentKey="jerkkApp.registration.gjrcs">Gjrcs</Translate>
                  </Label>
                  <AvField
                    id="registration-gjrcs"
                    type="string"
                    className="form-control"
                    name="gjrcs"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sfgjrzgxjsqyLabel">
                    <Translate contentKey="jerkkApp.registration.sfgjrzgxjsqy">Sfgjrzgxjsqy</Translate>
                  </Label>
                  <AvInput
                    id="registration-sfgjrzgxjsqy"
                    type="select"
                    className="form-control"
                    name="sfgjrzgxjsqy"
                    value={(!isNew && registrationEntity.sfgjrzgxjsqy) || 'YES'}
                  >
                    <option value="YES">
                      <Translate contentKey="jerkkApp.Decision.YES" />
                    </option>
                    <option value="NO">
                      <Translate contentKey="jerkkApp.Decision.NO" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="tdysjsLabel" for="tdysjs">
                    <Translate contentKey="jerkkApp.registration.tdysjs">Tdysjs</Translate>
                  </Label>
                  <AvField
                    id="registration-tdysjs"
                    type="text"
                    name="tdysjs"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="xyczLabel" for="xycz">
                    <Translate contentKey="jerkkApp.registration.xycz">Xycz</Translate>
                  </Label>
                  <AvField
                    id="registration-xycz"
                    type="text"
                    name="xycz"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 24, errorMessage: translate('entity.validation.maxlength', { max: 24 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="wlxwhdzclxLabel" for="wlxwhdzclx">
                    <Translate contentKey="jerkkApp.registration.wlxwhdzclx">Wlxwhdzclx</Translate>
                  </Label>
                  <AvField
                    id="registration-wlxwhdzclx"
                    type="text"
                    name="wlxwhdzclx"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 36, errorMessage: translate('entity.validation.maxlength', { max: 36 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="wlxwhdzclx1Label" for="wlxwhdzclx1">
                    <Translate contentKey="jerkkApp.registration.wlxwhdzclx1">Wlxwhdzclx 1</Translate>
                  </Label>
                  <AvField
                    id="registration-wlxwhdzclx1"
                    type="text"
                    name="wlxwhdzclx1"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sfxyxcLabel" for="sfxyxc">
                    <Translate contentKey="jerkkApp.registration.sfxyxc">Sfxyxc</Translate>
                  </Label>
                  <AvField
                    id="registration-sfxyxc"
                    type="text"
                    name="sfxyxc"
                    validate={{
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="rzjhgkfwLabel">
                    <Translate contentKey="jerkkApp.registration.rzjhgkfw">Rzjhgkfw</Translate>
                  </Label>
                  <AvInput
                    id="registration-rzjhgkfw"
                    type="select"
                    className="form-control"
                    name="rzjhgkfw"
                    value={(!isNew && registrationEntity.rzjhgkfw) || 'O1'}
                  >
                    <option value="O1">
                      <Translate contentKey="jerkkApp.RzjhgkfwEnum.O1" />
                    </option>
                    <option value="O2">
                      <Translate contentKey="jerkkApp.RzjhgkfwEnum.O2" />
                    </option>
                    <option value="O3">
                      <Translate contentKey="jerkkApp.RzjhgkfwEnum.O3" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="rzmbLabel">
                    <Translate contentKey="jerkkApp.registration.rzmb">Rzmb</Translate>
                  </Label>
                  <AvInput
                    id="registration-rzmb"
                    type="select"
                    className="form-control"
                    name="rzmb"
                    value={(!isNew && registrationEntity.rzmb) || 'R1'}
                  >
                    <option value="R1">
                      <Translate contentKey="jerkkApp.RzmbEnum.R1" />
                    </option>
                    <option value="R2">
                      <Translate contentKey="jerkkApp.RzmbEnum.R2" />
                    </option>
                    <option value="R3">
                      <Translate contentKey="jerkkApp.RzmbEnum.R3" />
                    </option>
                    <option value="R4">
                      <Translate contentKey="jerkkApp.RzmbEnum.R4" />
                    </option>
                    <option value="R5">
                      <Translate contentKey="jerkkApp.RzmbEnum.R5" />
                    </option>
                    <option value="R6">
                      <Translate contentKey="jerkkApp.RzmbEnum.R6" />
                    </option>
                    <option value="R7">
                      <Translate contentKey="jerkkApp.RzmbEnum.R7" />
                    </option>
                    <option value="R8">
                      <Translate contentKey="jerkkApp.RzmbEnum.R8" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lxrzwLabel" for="lxrzw">
                    <Translate contentKey="jerkkApp.registration.lxrzw">Lxrzw</Translate>
                  </Label>
                  <AvField
                    id="registration-lxrzw"
                    type="text"
                    name="lxrzw"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lxdhLabel" for="lxdh">
                    <Translate contentKey="jerkkApp.registration.lxdh">Lxdh</Translate>
                  </Label>
                  <AvField
                    id="registration-lxdh"
                    type="text"
                    name="lxdh"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lxyxLabel" for="lxyx">
                    <Translate contentKey="jerkkApp.registration.lxyx">Lxyx</Translate>
                  </Label>
                  <AvField
                    id="registration-lxyx"
                    type="text"
                    name="lxyx"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lxdzLabel" for="lxdz">
                    <Translate contentKey="jerkkApp.registration.lxdz">Lxdz</Translate>
                  </Label>
                  <AvField
                    id="registration-lxdz"
                    type="text"
                    name="lxdz"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 120, errorMessage: translate('entity.validation.maxlength', { max: 120 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ssly1Label" for="ssly1">
                    <Translate contentKey="jerkkApp.registration.ssly1">Ssly 1</Translate>
                  </Label>
                  <AvField
                    id="registration-ssly1"
                    type="text"
                    name="ssly1"
                    validate={{
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/registration" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  registrationEntity: storeState.registration.entity,
  jerkEntity: storeState.registration.jerk,
  loading: storeState.registration.loading,
  updating: storeState.registration.updating,
  updateSuccess: storeState.registration.updateSuccess
});

const mapDispatchToProps = {
  getJerkEntity,
  updateJerkEntity,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RegistrationUpdate);
