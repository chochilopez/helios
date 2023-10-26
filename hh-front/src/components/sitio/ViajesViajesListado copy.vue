<template>
  <q-card class="q-ma-md font-5 no-shadow no-border">
    <q-card-section>
      <div class="row justify-center">
        <div class="col-xs-6">
          <q-btn-dropdown class="" label="Buscar viajes por" dropdown-icon="change_history">
            <q-list>
              <q-item clickable v-close-popup @click="fMostrarAcoplado">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Acoplado</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarCamion">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Camion</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarCategoriaViaje">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Categoria viaje</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarComprador">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Comprador</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarConductor">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Conductor</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarDireccionCarga">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Dirección carga</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarDireccionDestino">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Dirección destino</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarDireccionOrigen">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Dirección origen</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarIntermediario">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Intermediario</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarKilometrosCargado">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Kilometros cargado</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarKilometrosVacio">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Kilometros vacio</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarNotas">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Notas</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarNumeroGuia">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Numero guía</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarPesoNeto">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Peso neto</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarTara">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Tara</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarValorKilomertro">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Valor kilometro</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup @click="fMostrarVendedor">
                <q-item-section avatar>
                  <q-icon color="primary" name="bluetooth" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Vendedor</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </div>

        <q-select
          v-if='editAcoplado'
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="acoplado"
          option-value="id"
          option-label="modelo"
          label="Buscar por modelo acoplado"
          use-input
          hide-selected
          fill-input
          :options="acoplados"
          @filter="fFiltrarAcoplados"
          @update:model-value="afBuscarPorAcopladoId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-truck-moving" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>

        <q-select
          v-if='editCamion'
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="camion"
          option-value="id"
          option-label="modelo"
          label="Buscar por modelo camion"
          use-input
          hide-selected
          fill-input
          :options="camiones"
          @filter="fFiltrarCamiones"
          @update:model-value="afBuscarPorCamionId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-truck-moving" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>

        <q-select
          v-if='editComprador'
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="comprador"
          :options="compradores"
          option-value="id"
          option-label="nombre"
          label="Buscar por comprador"
          use-input
          input-debounce="0"
          @filter="fFiltrarCompradores"
          @update:model-value="afBuscarPorCompradorId()"
        >
          <template v-slot:before>
            <q-icon name="monetization_on" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>

        <q-select
          v-if='editConductor'
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="conductor"
          :options="conductores"
          option-value="id"
          option-label="nombre"
          label="Buscar por conductor"
          use-input
          input-debounce="0"
          @filter="fFiltrarConductores"
          @update:model-value="afBuscarPorConductorId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="airline_seat_recline_extra" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>

        <q-select
          v-if='editDireccionDestino'
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="destino"
          :options="destinos"
          option-value="id"
          option-label="direccion"
          label="Buscar por destino"
          use-input
          input-debounce="0"
          @filter="fFiltrarDestinos"
          @update:model-value="afBuscarPorDestinoId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>

        <q-select
          v-if='editDireccionOrigen'
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="origen"
          :options="origenes"
          option-value="id"
          option-label="direccion"
          label="Buscar por origen"
          use-input
          input-debounce="0"
          @filter="fFiltrarOrigenes"
          @update:model-value="afBuscarPorOrigenId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location-dot" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>

        <q-select
          v-if='editVendedor'
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="vendedor"
          :options="vendedores"
          option-value="id"
          option-label="nombre"
          label="Buscar por vendedor"
          use-input
          input-debounce="0"
          @filter="fFiltrarVendedores"
          @update:model-value="afBuscarPorVendedorId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-cash-register" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>

      </div>
    </q-card-section>
  </q-card>
  <q-card>
    <q-card-section>
      <div class="row justify-start q-pb-md">
        <div class="col-lg-4 col-sm-5 col-xs-12 q-pa-md">
          <q-input
            mask="##-##-####"
            v-model="rangoFechas.from"
            outlined
            dense
            clearable
            label="Busqueda entre fechas - Inicio"
            hint="Ingresá la fecha con el formato dd-mm-yyyy. Ej: 20-01-2020"
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-greater-than" class="q-mx-xs" />
            </template>
            <template v-slot:prepend>
              <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                  <q-date v-model="rangoFechas.from" mask="DD-MM-YYYY">
                    <div class="row items-center justify-end">
                      <q-btn v-close-popup label="OK" color="primary" flat />
                    </div>
                  </q-date>
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </div>
        <div class="col-lg-4 col-sm-5 col-xs-12 q-pa-md">
          <q-input
            mask="##-##-####"
            v-model="rangoFechas.to"
            outlined
            dense
            clearable
            label="Busqueda entre fechas - Fin"
            hint="Ingresá la fecha con el formato dd-mm-yyyy. Ej: 30-01-2020"
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-less-than" class="q-mx-xs" />
            </template>
            <template v-slot:prepend>
              <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                  <q-date v-model="rangoFechas.to" mask="DD-MM-YYYY">
                    <div class="row items-center justify-end">
                      <q-btn v-close-popup label="OK" color="primary" flat />
                    </div>
                  </q-date>
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </div>
      </div>
    </q-card-section>
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        title="Viajes"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="viajes"
        row-key="id"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td auto-width class="text-center">
              <q-btn size="sm" class="text-white" :class="props.expand ? 'paleta5-fondo2' : 'paleta5-fondo3'" round dense @click="props.expand = !props.expand" :icon="props.expand ? 'remove' : 'add'" />
            </q-td>
            <q-td class="text-center">
              {{ props.row.id }}
            </q-td>
            <q-td>
              {{ props.row.conductor.nombre }}
            </q-td>
            <q-td>
              {{ props.row.camion.marca + " " + props.row.camion.modelo }}
            </q-td>
            <q-td>
              {{ props.row.vendedor.nombre }}
            </q-td>
            <q-td>
              {{ props.row.comprador.nombre }}
            </q-td>
            <q-td>
              {{ props.row.categoriaViaje.categoria }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.cantidadTransportada }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.kmVacio }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.kmCargado }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.valorKm }}
            </q-td>
          </q-tr>
          <q-tr v-show="props.expand" :props="props" class="paleta1-fondo4">
            <q-td colspan="100%">
              <div class="row">
                <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.id}}</div>
                  <div class="row paleta1-color2">Id</div>
                </div>
                <div v-if="props.row.cantidadTransportada != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.cantidadTransportada}}</div>
                  <div class="row paleta1-color2">Cantidad transportada</div>
                </div>
                <div v-if="props.row.valorKm != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.valorKm}}</div>
                  <div class="row paleta1-color2">Valor del kilometro</div>
                </div>
                <div v-if="props.row.neto != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.neto}}</div>
                  <div class="row paleta1-color2">Peso neto</div>
                </div>
                <div v-if="props.row.tara != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.tara}}</div>
                  <div class="row paleta1-color2">Tara</div>
                </div>
                <div v-if="props.row.kmCargado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.kmCargado}}</div>
                  <div class="row paleta1-color2">Kilometros cargado</div>
                </div>
                <div v-if="props.row.kmVacio != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.kmVacio}}</div>
                  <div class="row paleta1-color2">Kilometros vacio</div>
                </div>
                <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.notas}}</div>
                  <div class="row paleta1-color2">Notas</div>
                </div>
                <div v-if="props.row.guia != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.guia}}</div>
                  <div class="row paleta1-color2">N° guia</div>
                </div>
                <div v-if="props.row.categoriaViaje != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.categoriaViaje.categoria}}</div>
                  <div class="row paleta1-color2">Categoria viaje</div>
                </div>
                <div v-if="props.row.origen != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.origen.ubicacion.ciudad + "-" + props.row.origen.direccion}}</div>
                  <div class="row paleta1-color2">Origen viaje</div>
                </div>
                <div v-if="props.row.carga != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.carga.ubicacion.ciudad + "-" + props.row.carga.direccion}}</div>
                  <div class="row paleta1-color2">Direccion de carga</div>
                </div>
                <div v-if="props.row.destino != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.destino.ubicacion.ciudad + "-" + props.row.destino.direccion}}</div>
                  <div class="row paleta1-color2">Direccion de destino</div>
                </div>
                <div v-if="props.row.vendedor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.vendedor.nombre}}</div>
                  <div class="row paleta1-color2">Nombre vendedor</div>
                </div>
                <div v-if="props.row.intermediario != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.intermediario.nombre}}</div>
                  <div class="row paleta1-color2">Nombre intermediario</div>
                </div>
                <div v-if="props.row.comprador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.comprador.nombre}}</div>
                  <div class="row paleta1-color2">Nombre comprador</div>
                </div>
                <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.fecha.fecha}}</div>
                  <div class="row paleta1-color2">Fecha del viaje</div>
                </div>
                <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.conductor.nombre}}</div>
                  <div class="row paleta1-color2">Nombre conductor</div>
                </div>
                <div v-if="props.row.camion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.camion.marca}}</div>
                  <div class="row paleta1-color2">Camion</div>
                </div>
                <div v-if="props.row.acoplado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.acoplado.marca}}</div>
                  <div class="row paleta1-color2">Acoplado</div>
                </div>
                <div v-if="props.row.creador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.creador.username}}</div>
                  <div class="row paleta1-color2">Creador</div>
                </div>
                <div v-if="props.row.creado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.creado}}</div>
                  <div class="row paleta1-color2">Creado</div>
                </div>
                <div v-if="props.row.modificador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.modificador.username}}</div>
                  <div class="row paleta1-color2">Modificador</div>
                </div>
                <div v-if="props.row.modificado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.modificado}}</div>
                  <div class="row paleta1-color2">Modificado</div>
                </div>
                <div v-if="props.row.eliminador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.eliminador.username}}</div>
                  <div class="row paleta1-color2">Eliminador</div>
                </div>
                <div v-if="props.row.eliminado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.elimiando}}</div>
                  <div class="row paleta1-color2">Elimiando</div>
                </div>
              </div>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { notificarService } from 'src/helpers/notificar_service'
import { ViajeModel } from 'src/models/viaje_model'
import { conductorService } from 'src/services/conductor_service'
import { camionService } from 'src/services/camion_service'
import { viajeService } from 'src/services/viaje_service'
import { autenticacionService } from 'src/services/autenticacion_service'
import { rolEnum } from 'src/models/enums/rol_enum'
import { ayuda } from 'app/src/helpers/ayuda'
import { useQuasar } from 'quasar'
import { clienteService } from 'src/services/cliente_service'
import { direccionService } from 'src/services/direccion_service'
import { acopladoService } from 'src/services/acoplado_service'
import { categoriaViajeService } from 'src/services/categoria_viaje_service'
import { v4 as uuidv4 } from 'uuid'

import { llaveroService } from 'src/helpers/llavero_service'

const paginacion = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columnas = [
  {
    label: 'Expandir',
    align: 'center'
  },
  {
    name: 'id',
    label: 'Id',
    align: 'center',
    field: 'id',
    sortOrder: 'da',
    sortable: true
  },
  {
    name: 'conductor',
    label: 'Conductor',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'camion',
    label: 'Camion',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'vendedor',
    label: 'Vendedor',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'comprador',
    label: 'Comprador',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'categoria',
    label: 'Categoria',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'cantidadTransportada',
    label: 'Cantidad',
    align: 'center',
    field: 'cantidadTransportada'
  },
  {
    name: 'kmVacio',
    label: 'Km Vacio',
    align: 'center',
    field: 'kmVacio'
  },
  {
    name: 'kmCargado',
    label: 'Km Cargado',
    align: 'center',
    field: 'kmCargado'
  },
  {
    name: 'valorKm',
    label: 'Valor Km',
    align: 'center',
    field: 'valorKm'
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const sesion = ref(uuidv4())

    const editAcoplado = ref(false)
    const editCamion = ref(false)
    const editCategoriaViaje = ref(false)
    const editComprador = ref(false)
    const editConductor = ref(false)
    const editDireccionCarga = ref(false)
    const editDireccionDestino = ref(false)
    const editDireccionOrigen = ref(false)
    const editFechaViaje = ref(false)
    const editIntermediario = ref(false)
    const editKilometrosCargado = ref(false)
    const editKilometrosVacio = ref(false)
    const editNotas = ref(false)
    const editNumeroGuia = ref(false)
    const editPesoNeto = ref(false)
    const editTara = ref(false)
    const editValorKilomertro = ref(false)
    const editVendedor = ref(false)

    const autoridad = ref(autenticacionService.obtenerAutoridades())
    const acoplado = ref(null)
    const acoplados = ref(null)
    const acopladosList = ref([])
    const camion = ref(null)
    const camiones = ref([])
    const camionesList = ref([])
    const carga = ref(null)
    const categoriaViaje = ref(null)
    const comprador = ref(null)
    const compradores = ref([])
    const conductor = ref(null)
    const conductores = ref([])
    const conductoresList = ref([])
    const clientesList = ref([])
    const direccionesList = ref([])
    const destino = ref(null)
    const destinos = ref([])
    const fecha = ref(null)
    const intermediario = ref(null)
    const kmCargado = ref(null)
    const kmVacio = ref(null)
    const notas = ref(null)
    const guia = ref(null)
    const pesoNeto = ref(null)
    const origen = ref(null)
    const origenes = ref([])
    const tara = ref(null)
    const valorKm = ref(null)
    const vendedor = ref(null)
    const vendedores = ref([])
    const viaje = reactive(new ViajeModel())
    const viajes = ref([])

    const rangoFechas = ref({ from: null, to: null })

    afBuscarPaginadas()

    async function afBuscarAcoplados () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          if (llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConEliminadasConSesion', sesion.value) !== null) {
            acopladosList.value = llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConEliminadasConSesion', sesion.value).value
            console.log('AcopladoService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await acopladoService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              acopladosList.value = resultado.data
              console.log('AcopladoService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value) !== null) {
            acopladosList.value = llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value).value
            console.log('AcopladoService: Sesion recargada.')
          } else {
            resultado = await acopladoService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              acopladosList.value = resultado.data
              console.log('AcopladoService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarCamiones () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await camionService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada.')
          } else {
            resultado = await camionService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarClientes () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          if (llaveroService.obtenerDeLocalConSesion('hhClienteTodasConEliminadasConSesion', sesion.value) !== null) {
            clientesList.value = llaveroService.obtenerDeLocalConSesion('hhClienteTodasConEliminadasConSesion', sesion.value).value
            console.log('ClienteService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await clienteService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              clientesList.value = resultado.data
              console.log('ClienteService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhClienteTodasConSesion', sesion.value) !== null) {
            clientesList.value = llaveroService.obtenerDeLocalConSesion('hhClienteTodasConSesion', sesion.value).value
            console.log('ClienteService: Sesion recargada.')
          } else {
            resultado = await clienteService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              clientesList.value = resultado.data
              console.log('ClienteService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarConductores () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await conductorService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada.')
          } else {
            resultado = await conductorService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarDirecciones () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          if (llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConEliminadasConSesion', sesion.value) !== null) {
            direccionesList.value = llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConEliminadasConSesion', sesion.value).value
            console.log('DireccionService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await direccionService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              direccionesList.value = resultado.data
              console.log('DireccionService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConSesion', sesion.value) !== null) {
            direccionesList.value = llaveroService.obtenerDeLocalConSesion('hhDireccionTodasConSesion', sesion.value).value
            console.log('DireccionService: Sesion recargada.')
          } else {
            resultado = await direccionService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              direccionesList.value = resultado.data
              console.log('DireccionService: ' + resultado.headers.mensaje)
            }
          }
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarEntreFechas () {
      if (rangoFechas.value.from == null) {
        notificarService.notificarAlerta('El campo fecha desde no puede estar vacio.')
      } else if (rangoFechas.value.to == null) {
        notificarService.notificarAlerta('El campo fecha hasta no puede estar vacio.')
      } else if (rangoFechas.value.from > rangoFechas.value.to) {
        notificarService.notificarAlerta('El campo fecha desde no puede ser mayor que fecha hasta.')
      } else {
        $q.loading.show()
        try {
          if (vendedor.value != null || camion.value != null || conductor.value != null || comprador.value != null || origen.value != null || destino.value != null) {
            viajes.value = viajes.value.filter((objetoViaje) => {
              return ayuda.fFormatearDeBackend(objetoViaje.fecha.fecha) >= ayuda.fFormatearDeDatePicker(rangoFechas.value.from) &&
                ayuda.fFormatearDeBackend(objetoViaje.fecha.fecha) <= ayuda.fFormatearDeDatePicker(rangoFechas.value.to)
            })
          } else {
            let resultado = null
            if (autoridad.value.includes(rolEnum.ADMIN)) {
              resultado = await viajeService.spfBuscarTodasPorFechaViajeEntreFechasConEliminadas(
                ayuda.fFormatearDeDatePicker(rangoFechas.value.from),
                ayuda.fFormatearDeDatePicker(rangoFechas.value.to)
              )
            } else {
              resultado = await viajeService.spfBuscarTodasPorFechaViajeEntreFechas(
                ayuda.fFormatearDeDatePicker(rangoFechas.value.from),
                ayuda.fFormatearDeDatePicker(rangoFechas.value.to)
              )
            }
            if (resultado.status === 200) {
              console.log(resultado.headers.mensaje)
              viajes.value = resultado.data
            }
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPaginadas () {
      $q.loading.show()
      try {
        const paginadoDTO = {
          direccion: 'DESC',
          campo: 'creada',
          pagina: '0',
          elementos: '50'
        }
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await viajeService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          viajes.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          viajes.value = []
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    async function afBuscarPorCamionId () {
      if (camion.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('camion')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorCamionIdConEliminadas(camion.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCamionId(camion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            viajes.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorCompradorId () {
      if (comprador.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('comprador')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorCompradorIdConEliminadas(comprador.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCompradorId(comprador.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorConductorId () {
      if (conductor.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('conductor')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorConductorIdConEliminadas(conductor.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorConductorId(conductor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorDestinoId () {
      if (destino.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('destino')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorDireccionDestinoIdConEliminadas(destino.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDireccionDestinoId(destino.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorOrigenId () {
      if (origen.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('origen')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorDireccionOrigenIdConEliminadas(origen.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDireccionOrigenId(origen.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
          }
          if (err.response.status === 404) {
            viajes.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    async function afBuscarPorVendedorId () {
      if (vendedor.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('vendedor')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            console.log('aca')
            resultado = await viajeService.spfBuscarTodasPorVendedorIdConEliminadas(vendedor.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorVendedorId(vendedor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            viajes.value = []
          }
          if (err.response.status === 404) {
            viajes.value = []
            console.info(err.response.headers.mensaje)
            notificarService.infoAlerta(err.response.headers.mensaje)
          } else if (err.response.headers.mensaje) {
            console.warn('Advertencia: ' + err.response.headers.mensaje)
            notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
          } else {
            const mensaje = 'Hubo un error al intentar obtener el listado.'
            notificarService.notificarError(mensaje)
            console.error(mensaje)
          }
          $q.loading.hide()
        }
      }
    }

    function fFiltrarAcoplados (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        acoplados.value = acopladosList.value.filter(v => v.modelo.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarCamiones (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        camiones.value = camionesList.value.filter(v => v.modelo.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarCompradores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        compradores.value = clientesList.value.filter(v => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarConductores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        conductores.value = conductoresList.value.filter(v => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarDestinos (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        destinos.value = direccionesList.value.filter(v => v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarOrigenes (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        origenes.value = direccionesList.value.filter(v => v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarVendedores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        vendedores.value = clientesList.value.filter(v => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fLimpiarInputs () {
      editAcoplado.value = false
      acoplado.value = null
      editCamion.value = false
      camion.value = null
      editCategoriaViaje.value = false
      categoriaViaje.value = null
      editComprador.value = false
      comprador.value = null
      editConductor.value = false
      conductor.value = null
      editDireccionCarga.value = false
      carga.value = null
      editDireccionDestino.value = false
      destino.value = null
      editDireccionOrigen.value = false
      origen.value = null
      editFechaViaje.value = false
      fecha.value = null
      editIntermediario.value = false
      intermediario.value = null
      editKilometrosCargado.value = false
      kmCargado.value = null
      editKilometrosVacio.value = false
      kmVacio.value = null
      editNotas.value = false
      notas.value = null
      editNumeroGuia.value = false
      guia.value = null
      editPesoNeto.value = false
      pesoNeto.value = null
      editTara.value = false
      tara.value = null
      editValorKilomertro.value = false
      valorKm.value = null
      viajes.value = []
    }

    function fMostrarAcoplado () {
      afBuscarAcoplados().then(
        editAcoplado.value = true
      )
    }

    function fMostrarCamion () {
      afBuscarCamiones().then(
        editCamion.value = true
      )
    }

    function fMostrarCategoriaViaje () {
      // afBuscarCamiones().then(
      //   editCamion.value = true
      // )
    }

    function fMostrarComprador () {
      afBuscarClientes().then(
        editComprador.value = true
      )
    }

    function fMostrarConductor () {
      afBuscarConductores().then(
        editConductor.value = true
      )
    }

    function fMostrarDireccionCarga () {
      afBuscarDirecciones().then(
        editDireccionCarga.value = true
      )
    }

    function fMostrarDireccionDestino () {
      afBuscarDirecciones().then(
        editDireccionDestino.value = true
      )
    }

    function fMostrarDireccionOrigen () {
      afBuscarDirecciones().then(
        editDireccionOrigen.value = true
      )
    }

    function fMostrarFechaViaje () {
      // afBuscarCamiones().then(
      //   editCamion.value = true
      // )
    }

    function fMostrarIntermediario () {
      afBuscarClientes().then(
        editIntermediario.value = true
      )
    }

    function fMostrarKilometrosCargado () {
      editKilometrosCargado.value = true
    }

    function fMostrarKilometrosVacio () {
      editKilometrosVacio.value = true
    }

    function fMostrarNotas () {
      editNotas.value = true
    }

    function fMostrarNumeroGuia () {
      editNumeroGuia.value = true
    }

    function fMostrarPesoNeto () {
      editPesoNeto.value = true
    }

    function fMostrarTara () {
      editTara.value = true
    }

    function fMostrarValorKilomertro () {
      editValorKilomertro.value = true
    }

    function fMostrarVendedor () {
      afBuscarClientes().then(
        editVendedor.value = true
      )
    }

    return {
      afBuscarEntreFechas,
      afBuscarPorCamionId,
      afBuscarPorConductorId,
      afBuscarPorCompradorId,
      afBuscarPorDestinoId,
      afBuscarPorOrigenId,
      afBuscarPorVendedorId,

      editAcoplado,
      editCamion,
      editCategoriaViaje,
      editComprador,
      editConductor,
      editDireccionCarga,
      editDireccionDestino,
      editDireccionOrigen,
      editFechaViaje,
      editIntermediario,
      editKilometrosCargado,
      editKilometrosVacio,
      editNotas,
      editNumeroGuia,
      editPesoNeto,
      editTara,
      editValorKilomertro,
      editVendedor,

      autoridad,
      acoplado,
      acoplados,
      camion,
      camiones,
      conductor,
      conductores,
      columnas,
      comprador,
      compradores,
      destino,
      destinos,
      origen,
      origenes,
      paginacion,
      rangoFechas,
      vendedor,
      vendedores,
      viaje,
      viajes,

      fMostrarAcoplado,
      fMostrarCamion,
      fMostrarCategoriaViaje,
      fMostrarComprador,
      fMostrarConductor,
      fMostrarDireccionCarga,
      fMostrarDireccionDestino,
      fMostrarDireccionOrigen,
      fMostrarFechaViaje,
      fMostrarIntermediario,
      fMostrarKilometrosCargado,
      fMostrarKilometrosVacio,
      fMostrarNotas,
      fMostrarNumeroGuia,
      fMostrarPesoNeto,
      fMostrarTara,
      fMostrarValorKilomertro,
      fMostrarVendedor,

      fFiltrarAcoplados,
      fFiltrarCamiones,
      fFiltrarConductores,
      fFiltrarCompradores,
      fFiltrarDestinos,
      fFiltrarOrigenes,
      fFiltrarVendedores,
      fFormatoFecha,
      fLimpiarInputs
    }
  }
}
</script>
<style scoped>
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
</style>
