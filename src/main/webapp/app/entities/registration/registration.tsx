import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  translate,
  ICrudSearchAction,
  ICrudGetAllAction,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination,
  TextFormat
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './registration.reducer';
import { IRegistration } from 'app/shared/model/registration.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IRegistrationProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IRegistrationState extends IPaginationBaseState {
  search: string;
}

export class Registration extends React.Component<IRegistrationProps, IRegistrationState> {
  state: IRegistrationState = {
    search: '',
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.setState({ activePage: 1 }, () => {
        const { activePage, itemsPerPage, sort, order, search } = this.state;
        this.props.getSearchEntities(search, activePage - 1, itemsPerPage, `${sort},${order}`);
      });
    }
  };

  clear = () => {
    this.setState({ search: '', activePage: 1 }, () => {
      this.props.getEntities();
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order, search } = this.state;
    if (search) {
      this.props.getSearchEntities(search, activePage - 1, itemsPerPage, `${sort},${order}`);
    } else {
      this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
    }
  };

  render() {
    const { registrationList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="registration-heading">
          <Translate contentKey="jerkkApp.registration.home.title">Registrations</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jerkkApp.registration.home.createLabel">Create new Registration</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('jerkkApp.registration.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('registType')}>
                  <Translate contentKey="jerkkApp.registration.registType">Regist Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dwqc')}>
                  <Translate contentKey="jerkkApp.registration.dwqc">Dwqc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hxcpmc')}>
                  <Translate contentKey="jerkkApp.registration.hxcpmc">Hxcpmc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('zztjdw')}>
                  <Translate contentKey="jerkkApp.registration.zztjdw">Zztjdw</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dwhgrdz')}>
                  <Translate contentKey="jerkkApp.registration.dwhgrdz">Dwhgrdz</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('szqylx')}>
                  <Translate contentKey="jerkkApp.registration.szqylx">Szqylx</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ssly')}>
                  <Translate contentKey="jerkkApp.registration.ssly">Ssly</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('gscpjj')}>
                  <Translate contentKey="jerkkApp.registration.gscpjj">Gscpjj</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('mbkhsc')}>
                  <Translate contentKey="jerkkApp.registration.mbkhsc">Mbkhsc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dqzykh')}>
                  <Translate contentKey="jerkkApp.registration.dqzykh">Dqzykh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('gnwhjjx')}>
                  <Translate contentKey="jerkkApp.registration.gnwhjjx">Gnwhjjx</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('zljs')}>
                  <Translate contentKey="jerkkApp.registration.zljs">Zljs</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hxjsly')}>
                  <Translate contentKey="jerkkApp.registration.hxjsly">Hxjsly</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('kjcgzh')}>
                  <Translate contentKey="jerkkApp.registration.kjcgzh">Kjcgzh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('jmlyqk')}>
                  <Translate contentKey="jerkkApp.registration.jmlyqk">Jmlyqk</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('jscsd')}>
                  <Translate contentKey="jerkkApp.registration.jscsd">Jscsd</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('jzmsylqk')}>
                  <Translate contentKey="jerkkApp.registration.jzmsylqk">Jzmsylqk</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('jzysjs')}>
                  <Translate contentKey="jerkkApp.registration.jzysjs">Jzysjs</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fzrdh')}>
                  <Translate contentKey="jerkkApp.registration.fzrdh">Fzrdh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xb')}>
                  <Translate contentKey="jerkkApp.registration.xb">Xb</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('lxfs')}>
                  <Translate contentKey="jerkkApp.registration.lxfs">Lxfs</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('email')}>
                  <Translate contentKey="jerkkApp.registration.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fzrnl')}>
                  <Translate contentKey="jerkkApp.registration.fzrnl">Fzrnl</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tdpjnl')}>
                  <Translate contentKey="jerkkApp.registration.tdpjnl">Tdpjnl</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('gjrcs')}>
                  <Translate contentKey="jerkkApp.registration.gjrcs">Gjrcs</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('sfgjrzgxjsqy')}>
                  <Translate contentKey="jerkkApp.registration.sfgjrzgxjsqy">Sfgjrzgxjsqy</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tdysjs')}>
                  <Translate contentKey="jerkkApp.registration.tdysjs">Tdysjs</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('xycz')}>
                  <Translate contentKey="jerkkApp.registration.xycz">Xycz</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('wlxwhdzclx')}>
                  <Translate contentKey="jerkkApp.registration.wlxwhdzclx">Wlxwhdzclx</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('wlxwhdzclx1')}>
                  <Translate contentKey="jerkkApp.registration.wlxwhdzclx1">Wlxwhdzclx 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('sfxyxc')}>
                  <Translate contentKey="jerkkApp.registration.sfxyxc">Sfxyxc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('rzjhgkfw')}>
                  <Translate contentKey="jerkkApp.registration.rzjhgkfw">Rzjhgkfw</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('rzmb')}>
                  <Translate contentKey="jerkkApp.registration.rzmb">Rzmb</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('lxrzw')}>
                  <Translate contentKey="jerkkApp.registration.lxrzw">Lxrzw</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('lxdh')}>
                  <Translate contentKey="jerkkApp.registration.lxdh">Lxdh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('lxyx')}>
                  <Translate contentKey="jerkkApp.registration.lxyx">Lxyx</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('lxdz')}>
                  <Translate contentKey="jerkkApp.registration.lxdz">Lxdz</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ssly1')}>
                  <Translate contentKey="jerkkApp.registration.ssly1">Ssly 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createdDate')}>
                  <Translate contentKey="jerkkApp.registration.createdDate">Created Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('modifiedDate')}>
                  <Translate contentKey="jerkkApp.registration.modifiedDate">Modified Date</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {registrationList.map((registration, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${registration.id}`} color="link" size="sm">
                      {registration.id}
                    </Button>
                  </td>
                  <td>{registration.registType}</td>
                  <td>{registration.dwqc}</td>
                  <td>{registration.hxcpmc}</td>
                  <td>{registration.zztjdw}</td>
                  <td>{registration.dwhgrdz}</td>
                  <td>{registration.szqylx}</td>
                  <td>{registration.ssly}</td>
                  <td>{registration.gscpjj}</td>
                  <td>{registration.mbkhsc}</td>
                  <td>{registration.dqzykh}</td>
                  <td>{registration.gnwhjjx}</td>
                  <td>
                    <Translate contentKey={`jerkkApp.Decision.${registration.zljs}`} />
                  </td>
                  <td>
                    <Translate contentKey={`jerkkApp.HxjslyEnum.${registration.hxjsly}`} />
                  </td>
                  <td>
                    <Translate contentKey={`jerkkApp.KjcgzhEnum.${registration.kjcgzh}`} />
                  </td>
                  <td>
                    <Translate contentKey={`jerkkApp.JmlyqkEnum.${registration.jmlyqk}`} />
                  </td>
                  <td>{registration.jscsd}</td>
                  <td>{registration.jzmsylqk}</td>
                  <td>{registration.jzysjs}</td>
                  <td>{registration.fzrdh}</td>
                  <td>
                    <Translate contentKey={`jerkkApp.XbEnum.${registration.xb}`} />
                  </td>
                  <td>{registration.lxfs}</td>
                  <td>{registration.email}</td>
                  <td>
                    <Translate contentKey={`jerkkApp.AgeEnum.${registration.fzrnl}`} />
                  </td>
                  <td>
                    <Translate contentKey={`jerkkApp.AgeEnum.${registration.tdpjnl}`} />
                  </td>
                  <td>{registration.gjrcs}</td>
                  <td>
                    <Translate contentKey={`jerkkApp.Decision.${registration.sfgjrzgxjsqy}`} />
                  </td>
                  <td>{registration.tdysjs}</td>
                  <td>{registration.xycz}</td>
                  <td>{registration.wlxwhdzclx}</td>
                  <td>{registration.wlxwhdzclx1}</td>
                  <td>{registration.sfxyxc}</td>
                  <td>
                    <Translate contentKey={`jerkkApp.RzjhgkfwEnum.${registration.rzjhgkfw}`} />
                  </td>
                  <td>
                    <Translate contentKey={`jerkkApp.RzmbEnum.${registration.rzmb}`} />
                  </td>
                  <td>{registration.lxrzw}</td>
                  <td>{registration.lxdh}</td>
                  <td>{registration.lxyx}</td>
                  <td>{registration.lxdz}</td>
                  <td>{registration.ssly1}</td>
                  <td>
                    <TextFormat value={registration.createdDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
                  </td>
                  <td>
                    <TextFormat value={registration.modifiedDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${registration.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${registration.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${registration.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ registration }: IRootState) => ({
  registrationList: registration.entities,
  totalItems: registration.totalItems
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Registration);
