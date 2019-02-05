import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity, updateEntity } from './registration.reducer';
import { IRegistration } from 'app/shared/model/registration.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import registration from './registration';
import { cleanEntity } from 'app/shared/util/entity-utils';
export interface IRegistrationDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class RegistrationDetail extends React.Component<IRegistrationDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }
  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  handleClose = () => {
    this.props.history.push('/entity/registration');
  };
  setActive(registrationEntity, sts) {
    let _registration = cleanEntity(registrationEntity);
    _registration.fbzt = sts ? 1 : 0;
    console.log('in setactive');
    this.props.updateEntity(_registration);
  }
  render() {
    const { registrationEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jerkkApp.registration.detail.title">Registration</Translate> [<b>{registrationEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="registType">
                <Translate contentKey="jerkkApp.registration.registType">Regist Type</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.registType}</dd>
            <dt>
              <span id="dwqc">
                <Translate contentKey="jerkkApp.registration.dwqc">Dwqc</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.dwqc}</dd>
            <dt>
              <span id="hxcpmc">
                <Translate contentKey="jerkkApp.registration.hxcpmc">Hxcpmc</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.hxcpmc}</dd>
            <dt>
              <span id="zztjdw">
                <Translate contentKey="jerkkApp.registration.zztjdw">Zztjdw</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.zztjdw}</dd>
            <dt>
              <span id="dwhgrdz">
                <Translate contentKey="jerkkApp.registration.dwhgrdz">Dwhgrdz</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.dwhgrdz}</dd>
            <dt>
              <span id="szqylx">
                <Translate contentKey="jerkkApp.registration.szqylx">Szqylx</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.szqylx}</dd>
            <dt>
              <span id="ssly">
                <Translate contentKey="jerkkApp.registration.ssly">Ssly</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.ssly}</dd>
            <dt>
              <span id="gscpjj">
                <Translate contentKey="jerkkApp.registration.gscpjj">Gscpjj</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.gscpjj}</dd>
            <dt>
              <span id="mbkhsc">
                <Translate contentKey="jerkkApp.registration.mbkhsc">Mbkhsc</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.mbkhsc}</dd>
            <dt>
              <span id="dqzykh">
                <Translate contentKey="jerkkApp.registration.dqzykh">Dqzykh</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.dqzykh}</dd>
            <dt>
              <span id="gnwhjjx">
                <Translate contentKey="jerkkApp.registration.gnwhjjx">Gnwhjjx</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.gnwhjjx}</dd>
            <dt>
              <span id="zljs">
                <Translate contentKey="jerkkApp.registration.zljs">Zljs</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.zljs}</dd>
            <dt>
              <span id="hxjsly">
                <Translate contentKey="jerkkApp.registration.hxjsly">Hxjsly</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.hxjsly}</dd>
            <dt>
              <span id="kjcgzh">
                <Translate contentKey="jerkkApp.registration.kjcgzh">Kjcgzh</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.kjcgzh}</dd>
            <dt>
              <span id="jmlyqk">
                <Translate contentKey="jerkkApp.registration.jmlyqk">Jmlyqk</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.jmlyqk}</dd>
            <dt>
              <span id="jscsd">
                <Translate contentKey="jerkkApp.registration.jscsd">Jscsd</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.jscsd}</dd>
            <dt>
              <span id="jzmsylqk">
                <Translate contentKey="jerkkApp.registration.jzmsylqk">Jzmsylqk</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.jzmsylqk}</dd>
            <dt>
              <span id="jzysjs">
                <Translate contentKey="jerkkApp.registration.jzysjs">Jzysjs</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.jzysjs}</dd>
            <dt>
              <span id="fzrdh">
                <Translate contentKey="jerkkApp.registration.fzrdh">Fzrdh</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.fzrdh}</dd>
            <dt>
              <span id="xb">
                <Translate contentKey="jerkkApp.registration.xb">Xb</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.xb}</dd>
            <dt>
              <span id="lxfs">
                <Translate contentKey="jerkkApp.registration.lxfs">Lxfs</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.lxfs}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="jerkkApp.registration.email">Email</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.email}</dd>
            <dt>
              <span id="fzrnl">
                <Translate contentKey="jerkkApp.registration.fzrnl">Fzrnl</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.fzrnl}</dd>
            <dt>
              <span id="tdpjnl">
                <Translate contentKey="jerkkApp.registration.tdpjnl">Tdpjnl</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.tdpjnl}</dd>
            <dt>
              <span id="gjrcs">
                <Translate contentKey="jerkkApp.registration.gjrcs">Gjrcs</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.gjrcs}</dd>
            <dt>
              <span id="sfgjrzgxjsqy">
                <Translate contentKey="jerkkApp.registration.sfgjrzgxjsqy">Sfgjrzgxjsqy</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.sfgjrzgxjsqy}</dd>
            <dt>
              <span id="tdysjs">
                <Translate contentKey="jerkkApp.registration.tdysjs">Tdysjs</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.tdysjs}</dd>
            <dt>
              <span id="xycz">
                <Translate contentKey="jerkkApp.registration.xycz">Xycz</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.xycz}</dd>
            <dt>
              <span id="wlxwhdzclx">
                <Translate contentKey="jerkkApp.registration.wlxwhdzclx">Wlxwhdzclx</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.wlxwhdzclx}</dd>
            <dt>
              <span id="wlxwhdzclx1">
                <Translate contentKey="jerkkApp.registration.wlxwhdzclx1">Wlxwhdzclx 1</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.wlxwhdzclx1}</dd>
            <dt>
              <span id="sfxyxc">
                <Translate contentKey="jerkkApp.registration.sfxyxc">Sfxyxc</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.sfxyxc}</dd>
            <dt>
              <span id="rzjhgkfw">
                <Translate contentKey="jerkkApp.registration.rzjhgkfw">Rzjhgkfw</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.rzjhgkfw}</dd>
            <dt>
              <span id="rzmb">
                <Translate contentKey="jerkkApp.registration.rzmb">Rzmb</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.rzmb}</dd>
            <dt>
              <span id="lxrzw">
                <Translate contentKey="jerkkApp.registration.lxrzw">Lxrzw</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.lxrzw}</dd>
            <dt>
              <span id="lxdh">
                <Translate contentKey="jerkkApp.registration.lxdh">Lxdh</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.lxdh}</dd>
            <dt>
              <span id="lxyx">
                <Translate contentKey="jerkkApp.registration.lxyx">Lxyx</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.lxyx}</dd>
            <dt>
              <span id="lxdz">
                <Translate contentKey="jerkkApp.registration.lxdz">Lxdz</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.lxdz}</dd>
            <dt>
              <span id="ssly1">
                <Translate contentKey="jerkkApp.registration.ssly1">Ssly 1</Translate>
              </span>
            </dt>
            <dd>{registrationEntity.ssly1}</dd>
            <dt>
              <span id="createdDate">
                <Translate contentKey="jerkkApp.registration.createdDate">Created Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={registrationEntity.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
            <dt>
              <span id="modifiedDate">
                <Translate contentKey="jerkkApp.registration.modifiedDate">Modified Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={registrationEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/registration" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/registration/${registrationEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
          &nbsp;
          {registrationEntity.id && registrationEntity.fbzt == 1 ? (
            <Button onClick={() => this.setActive(registrationEntity, false)} color="info">
              <FontAwesomeIcon icon="unlock" />{' '}
              <span className="d-none d-md-inline">
                <Translate contentKey="entity.action.offline">Offline</Translate>
              </span>
            </Button>
          ) : (
            <Button onClick={() => this.setActive(registrationEntity, true)} color="info">
              <FontAwesomeIcon icon="lock" />{' '}
              <span className="d-none d-md-inline">
                <Translate contentKey="entity.action.online">Online</Translate>
              </span>
            </Button>
          )}
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ registration }: IRootState) => ({
  registrationEntity: registration.entity,
  loading: registration.loading,
  updating: registration.updating,
  updateSuccess: registration.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RegistrationDetail);
